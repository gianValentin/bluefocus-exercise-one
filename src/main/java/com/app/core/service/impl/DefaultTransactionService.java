package com.app.core.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.app.core.entity.dto.currency.CurrencyDTO;
import com.app.core.entity.dto.transaction.TransactionAuditDTO;
import com.app.core.entity.dto.transaction.TransactionDTO;
import com.app.core.entity.model.CurrencyModel;
import com.app.core.entity.model.TransactionModel;
import com.app.core.entity.model.UserModel;
import com.app.core.security.repository.CurrencyRepository;
import com.app.core.security.repository.TransactionRepository;
import com.app.core.service.TransactionService;
import com.app.core.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DefaultTransactionService implements TransactionService {

	private final ModelMapper modelMapper;
	private final UserService userService;
	private final TransactionRepository transactionRepository;
	private final CurrencyRepository currencyRepository;

	@Override
	@Transactional
	public TransactionDTO generateTransaction(CurrencyDTO originCurrencyDTO, CurrencyDTO fateCurrencyDTO,
			Double amount) {
		Assert.notNull(amount, "Amount cannot be null");

		TransactionModel transactionCalculated = calculation(originCurrencyDTO, fateCurrencyDTO, amount, null);
		return modelMapper.map(transactionCalculated, TransactionDTO.class);
	}

	@Override
	public TransactionDTO findById(Integer id) {
		TransactionModel transaction = transactionFindById(id);
		return modelMapper.map(transaction, TransactionDTO.class);
	}

	@Override
	public TransactionDTO update(Integer id, CurrencyDTO originCurrencyDTO, CurrencyDTO fateCurrencyDTO,
			Double amount) {
		Assert.notNull(amount, "Amount cannot be null");
		TransactionModel transaction = transactionFindById(id);
		TransactionModel transactionCalculated = calculation(originCurrencyDTO, fateCurrencyDTO, amount,
				transaction.getId());
		return modelMapper.map(transactionCalculated, TransactionDTO.class);
	}

	private TransactionModel transactionFindById(Integer id) {
		return transactionRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));
	}

	private TransactionModel calculation(CurrencyDTO originCurrencyDTO, CurrencyDTO fateCurrencyDTO, Double amount,
			Integer transcarionId) {
		
		UserModel userSession = userService.getSessionUser();	

		CurrencyModel originCurrency = currencyRepository.findById(originCurrencyDTO.getId())
				.orElseThrow(() -> new RuntimeException("Oringin currency not found"));
		CurrencyModel fateCurrency = currencyRepository.findById(fateCurrencyDTO.getId())
				.orElseThrow(() -> new RuntimeException("Fate currency not found"));

		Double originValue = originCurrency.getValue();
		Double fateValue = fateCurrency.getValue();

		Double amonuntOrigin = amount / originValue;
		Double amountFate = amonuntOrigin * fateValue;

		BigDecimal round = new BigDecimal(amountFate).setScale(2, RoundingMode.HALF_UP);

		TransactionModel transaction = TransactionModel
				.builder()
				.user(userSession)
				.originAmoun(amount)
				.fateAmount(round.doubleValue())
				.originCurrency(originCurrency)
				.fateCurrency(fateCurrency)
				.build();

		if (ObjectUtils.isNotEmpty(transcarionId)) {
			transaction.setId(transcarionId);
		}

		return transactionRepository.save(transaction);
	}

	@Override
	public List<TransactionAuditDTO> audit() {
		List<TransactionModel> transactions = transactionRepository.findAll();
		return transactions.stream().map(transaction -> modelMapper.map(transaction, TransactionAuditDTO.class)).collect(Collectors.toList());
	}

}
