package com.lowes.bankingapp.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lowes.bankingapp.account.model.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account,Integer>{

}
