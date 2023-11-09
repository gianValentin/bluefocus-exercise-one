package com.app.core.security.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.core.entity.dto.currency.CurrencyDTO;
import com.app.core.entity.dto.transaction.TransactionDTO;
import com.app.core.entity.model.CurrencyModel;
import com.app.core.entity.model.TransactionModel;
import com.app.core.security.repository.CurrencyRepository;
import com.app.core.security.repository.TransactionRepository;
import com.app.core.security.service.TransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DefaultTransactionService implements TransactionService {
	
	private final ModelMapper modelMapper;
	private final TransactionRepository transactionRepository;
	private final CurrencyRepository currencyRepository;

	@Override
	@Transactional
	public TransactionDTO generateTransaction(CurrencyDTO originCurrencyDTO, CurrencyDTO fateCurrencyDTO, Double amount) {
		
		CurrencyModel originCurrency = currencyRepository.findById(originCurrencyDTO.getId()).orElseThrow(() -> new RuntimeException("Oringin currency not found"));
		CurrencyModel fateCurrency = currencyRepository.findById(fateCurrencyDTO.getId()).orElseThrow(() -> new RuntimeException("Fate currency not found"));
		
		Double originValue = originCurrency.getValue();
		Double fateValue = fateCurrency.getValue();
		
		Double amonuntOrigin =  amount / originValue;
		Double amountFate = amonuntOrigin * fateValue;
		
		TransactionModel transaction = TransactionModel
				.builder()
				.originAmoun(amount)
				.fateAmount(amountFate)
				.originCurrency(originCurrency)
				.fateCurrency(fateCurrency)
				.build();
		
		TransactionModel transactionSaved = transactionRepository.save(transaction);
		
		return modelMapper.map(transactionSaved, TransactionDTO.class);
	}

}
