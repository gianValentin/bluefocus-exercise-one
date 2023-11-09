package com.app.core.entity.dto.transaction;

import com.app.core.entity.dto.currency.CurrencyDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class GenerateTransactionDTO {
	private Double amount;
	private CurrencyDTO originCurrency;
	private CurrencyDTO fateCurrency;
}
