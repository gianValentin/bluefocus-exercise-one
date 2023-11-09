package com.app.core.entity.dto.transaction;

import com.app.core.entity.dto.GetUserDto;
import com.app.core.entity.dto.currency.CurrencyDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionAuditDTO {	
	private Integer id;
	private Double originAmoun;
	private Double fateAmount;
	private CurrencyDTO originCurrency;
	private CurrencyDTO fateCurrency;
	private GetUserDto user;
}
