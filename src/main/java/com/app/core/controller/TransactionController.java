package com.app.core.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.core.entity.dto.transaction.GenerateTransactionDTO;
import com.app.core.entity.dto.transaction.TransactionAuditDTO;
import com.app.core.entity.dto.transaction.TransactionDTO;
import com.app.core.service.TransactionService;

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
	
	@GetMapping("/{id}")
	public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Integer id){
		return ResponseEntity.status(HttpStatus.OK).body(transactionService.findById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TransactionDTO> updateTransaction(@PathVariable Integer id, @RequestBody GenerateTransactionDTO req){
		return ResponseEntity.status(HttpStatus.OK).body(transactionService.update(id, req.getOriginCurrency(),req.getFateCurrency(), req.getAmount()));
	}
	
	@GetMapping("/audit")
	public ResponseEntity<List<TransactionAuditDTO>> getAudit(){
		return ResponseEntity.status(HttpStatus.OK).body(transactionService.audit());
	}
}
