package com.app.core.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.core.entity.model.CurrencyModel;

public interface CurrencyRepository extends JpaRepository<CurrencyModel, Integer>{

}
