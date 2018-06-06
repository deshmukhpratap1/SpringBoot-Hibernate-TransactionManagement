package com.example.demo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entities.Account;
import com.example.demo.entities.TransferRequest;
import com.example.demo.service.AccountService;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;

@RestController
public class AccountController {

	@Autowired
	AccountService accountService;

	@PostMapping("/accounts")
	public ResponseEntity<Object> createAccount(@RequestBody Account account) {
		return accountService.createAccount(account);
	}

	@GetMapping("/accounts") // getAllAccounts
	public List<Account> getAllAccounts() {
		return accountService.getAllAccountDetails();
	}

	@GetMapping("/accounts/{id}")
	public Resource<Account> getAccount(@PathVariable int id) {
		return accountService.getAccountDetails(id);
	}

	@PutMapping("/accounts/{id}")
	public ResponseEntity<Object> updateAccount(@RequestBody Account account, @PathVariable int id) {
		return accountService.updateAccount(account, id);
	}

	@DeleteMapping("/accounts/{id}")
	public void deleteAccount(@PathVariable int id) {
		accountService.deleteAccount(id);
	}

	@PostMapping("/transferFund")
	public @ResponseBody Resource<Account> transferFund(@RequestBody TransferRequest transferRequest) {
		System.out.println("transferRequest=" + transferRequest.toString());
		Resource<Account> account = null;
		try {
			account = accountService.transferFund(transferRequest.getFrom(), transferRequest.getTo(),
					transferRequest.getAmount());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return account;
	}

}
