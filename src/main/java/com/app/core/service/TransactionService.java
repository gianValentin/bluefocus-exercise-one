package com.app.core.service;

import java.util.List;

import com.app.core.entity.dto.currency.CurrencyDTO;
import com.app.core.entity.dto.transaction.TransactionAuditDTO;
import com.app.core.entity.dto.transaction.TransactionDTO;

public interface TransactionService {
	TransactionDTO generateTransaction(CurrencyDTO originCurrency, CurrencyDTO fateCurrency, Double Amount);

	TransactionDTO findById(Integer id);

	TransactionDTO update(Integer id, CurrencyDTO originCurrencyDTO, CurrencyDTO fateCurrencyDTO, Double amount);

	List<TransactionAuditDTO> audit();
}
