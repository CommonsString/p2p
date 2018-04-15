package com.base.service;

import com.base.domain.Account;

/**
 * 账户相关信息
 */
public interface AccountService {
	
	/**
	 * update支持乐观锁
	 */
	void update(Account account);
	
	void addAccount(Account account); //添加
	
	Account getAccountById(String accountId);
	
}
