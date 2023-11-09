package com.app.core.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.core.entity.dto.currency.CurrencyDTO;
import com.app.core.entity.model.CurrencyModel;
import com.app.core.security.repository.CurrencyRepository;
import com.app.core.service.CurrencyService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DefaultCurrencyService implements CurrencyService {

	private final ModelMapper modelMapper;
	private final CurrencyRepository currencyRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<CurrencyDTO> findAll() {
		List<CurrencyModel> currencies = currencyRepository.findAll();
		return currencies.stream().map(currency -> modelMapper.map(currency, CurrencyDTO.class)).collect(Collectors.toList());
	}

}
