package com.app.core.service;

import java.util.List;

import com.app.core.entity.dto.currency.CurrencyDTO;

public interface CurrencyService {
	List<CurrencyDTO> findAll();
}
