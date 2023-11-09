package com.app.core.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.core.entity.dto.currency.CurrencyDTO;
import com.app.core.service.CurrencyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/currency")
@RequiredArgsConstructor
public class CurrencyController {
	
	private final CurrencyService currencyService;

	@GetMapping
	public ResponseEntity<List<CurrencyDTO>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(currencyService.findAll());
	}
}
