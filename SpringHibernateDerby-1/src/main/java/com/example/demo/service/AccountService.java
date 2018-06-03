package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AccountRepository;
import com.example.demo.entities.Account;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;
	
	public List<Account>getAllAccountDetails(){
		List<Account> accounts= new ArrayList<Account>();
			accountRepository.findAll()
				.forEach(accounts::add);
		return accounts;
	}
	
	public Account createAccount(Account account){
		return accountRepository.save(account);
	}

	public Account getAccountDetails(int id) {
		// accountRepository.findById(id);
		return null;
	}

	public boolean transferFund(int from, int to) {
		Account fromAccount= this.getAccountDetails(from);
		Account toAccount= this.getAccountDetails(to);
		return false;
	}
	
	
}
