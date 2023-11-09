package com.app.core.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.core.entity.model.TransactionModel;

public interface TransactionRepository extends JpaRepository<TransactionModel, Integer>{

}
