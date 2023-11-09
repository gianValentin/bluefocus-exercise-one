package com.app.core.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.core.entity.dto.transaction.GenerateTransactionDTO;
import com.app.core.entity.dto.transaction.TransactionDTO;
import com.app.core.security.service.TransactionService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {
	private final TransactionService transactionService;
	
	@PostMapping
	public ResponseEntity<TransactionDTO>generateTransaction(@RequestBody GenerateTransactionDTO req){
		TransactionDTO transaction = transactionService.generateTransaction(req.getOriginCurrency(),req.getFateCurrency(), req.getAmount());
		return ResponseEntity.status(HttpStatus.OK).body(transaction);
	}
	
	
}
