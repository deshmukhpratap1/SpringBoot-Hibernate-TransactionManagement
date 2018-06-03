package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.AccountService;
import com.example.demo.entities.*;

@RestController
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@RequestMapping("/accounts")   //getAllAccounts
	public List<Account> getAllAccounts(){
		return accountService.getAllAccountDetails();
	}
	 
	
	@RequestMapping("/accounts/id")
	public Account getAccount( @PathVariable int id){
		return accountService.getAccountDetails(id);
	} 
	
	@RequestMapping(method= RequestMethod.POST , value="/createAccount")
	public void createAccount(@RequestBody Account account){
		accountService.createAccount(account);
	}
	
	@RequestMapping(method= RequestMethod.POST , value="/transferFund")
	public boolean transferFund(@RequestBody int from, @RequestBody int to){
		return accountService.transferFund(from, to);
	}
	
	
	
}
