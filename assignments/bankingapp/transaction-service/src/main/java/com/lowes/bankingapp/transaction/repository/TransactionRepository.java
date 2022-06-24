package com.lowes.bankingapp.transaction.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lowes.bankingapp.transaction.model.Transaction;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer>{

}
