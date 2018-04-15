package com.base.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.domain.Account;
import com.base.mapper.AccountMapper;
import com.base.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountMapper mapper;

	@Override
	public void update(Account account) {
		int result = mapper.updateAccountByIdAndVersion(account);
		if(result == 0){
			throw new RuntimeException("乐观锁失败，对象Account::" + account.getAccountId());
		}
	}

	@Override
	public void addAccount(Account account) {
		int result = this.mapper.addAccount(account);
		if(result == 0){
			throw new RuntimeException("添加账户信息失败！");
		}
	}

	@Override
	public Account getAccountById(String accountId) {
		return this.mapper.getAccountById(accountId);
	}
}
