package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.dao.AccountRepository;
import com.example.demo.entities.Account;
import com.example.demo.exception.AccountDoesNotExistException;
import com.example.demo.exception.NoEnoughBalanceException;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;

	public List<Account> getAllAccountDetails() {
		List<Account> accounts = new ArrayList<Account>();
		accountRepository.findAll().forEach(accounts::add);
		return accounts;
	}
	
	public ResponseEntity<Object> createAccount(Account account) {
		Account savedAccount = accountRepository.save(account);

	  URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		        .buildAndExpand(savedAccount.getAcctId()).toUri();
		    return ResponseEntity.created(location).build();
	}


	public Resource<Account> getAccountDetails(int id) {
		Optional<Account> account = accountRepository.findById(id);

		if (!account.isPresent())
			throw new AccountDoesNotExistException("id-" + id);

		Resource<Account> resource = new Resource<Account>(account.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllAccountDetails());
		resource.add(linkTo.withRel("all-accounts"));
		return resource;
	}

	@Transactional(readOnly = true)
	public Resource<Account> transferFund(int from, int to, int amount) throws Exception {
		Resource<Account> fromAccount = this.getAccountDetails(from);
		System.out.println("1");
		Resource<Account> toAccount = this.getAccountDetails(to);
		System.out.println("2");
		try {

			fromAccount.getContent().setBalance(fromAccount.getContent().getBalance() - amount);
			System.out.println("3");
			toAccount.getContent().setBalance(fromAccount.getContent().getBalance() + amount);
			System.out.println("4");
			accountRepository.save(fromAccount.getContent());
			System.out.println("5");
			accountRepository.save(toAccount.getContent());
			System.out.println("6");
			
			if (fromAccount.getContent().getBalance() < amount) {
				throw new NoEnoughBalanceException("No enougn balance to transfer");
			}
		} catch (Exception e) {
			throw new Exception();
		}
		return toAccount;
	}

	public void deleteAccount(int id) {
		accountRepository.deleteById(id);
	}

	public ResponseEntity<Object> updateAccount(Account account, int id) {
		Optional<Account> accountOptional = accountRepository.findById(id);

	    if (!accountOptional.isPresent())
	      return ResponseEntity.notFound().build();

	    account.setAcctId(id);
	    
	    accountRepository.save(account);

	    return ResponseEntity.noContent().build();
	}


}
