package com.app.core.security.service;

import com.app.core.entity.dto.currency.CurrencyDTO;
import com.app.core.entity.dto.transaction.TransactionDTO;

public interface TransactionService {
	TransactionDTO generateTransaction(CurrencyDTO originCurrency, CurrencyDTO fateCurrency, Double Amount);
}
