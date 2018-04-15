package com.base.domain;

import java.math.BigDecimal;

import com.base.tools.BidConst;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Account {
	
	private String accountId;
	
	private int version;
	private String tradePassword; //交易密码
	private BigDecimal usableAmount = BidConst.ZERO; //可用金额
	private BigDecimal freezedAmount = BidConst.ZERO; //冻结金额
	private BigDecimal unReceiveInterest = BidConst.ZERO; //代收利息
	private BigDecimal unReceivePrincipal = BidConst.ZERO; //代收本金
	private BigDecimal unReturnAmount = BidConst.ZERO; //代还金额
	private BigDecimal remainBorrowLimit = BidConst.BORROW_LIMIT; //账户剩余授信额度
	private BigDecimal borrowLimit = BidConst.BORROW_LIMIT;  // 账户授信额度
	
	//返回账户总额 = 可用金额+冻结金额+代收本金
	public BigDecimal getSumMoney(){ 
		return usableAmount.add(this.freezedAmount).add(this.unReceivePrincipal);
	}
}
