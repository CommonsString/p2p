package com.base.mapper;

import com.base.domain.Account;

public interface AccountMapper {
	
	int addAccount(Account account); //插入，添加账户信息
	 
	int updateAccountByIdAndVersion(Account account); //更新，账户信息
	
	Account getAccountById(String accountId);

}
