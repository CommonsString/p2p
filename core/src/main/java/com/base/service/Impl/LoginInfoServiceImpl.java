package com.base.service.Impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.base.domain.Account;
import com.base.domain.IpLog;
import com.base.domain.LoginInfo;
import com.base.domain.UserInfo;
import com.base.mapper.LoginInfoMapper;
import com.base.service.AccountService;
import com.base.service.IpLogService;
import com.base.service.LoginInfoService;
import com.base.service.UserInfoService;
import com.base.tools.BidConst;
import com.base.tools.UserContext;

@Service
public class LoginInfoServiceImpl implements LoginInfoService{
	
	@Autowired
	private LoginInfoMapper mapper;
	
	@Autowired
	private UserInfoService userInfoService; 
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private IpLogService ipLogService;
	

	@Override
	public LoginInfo getLoginInfo(String id) {
		return mapper.getLoginInfo(id);
	}

	@Override
	public void registerUser(String userName, String password) {
		int isCopy = this.mapper.getUserIsCopy(userName); //判断用户名是否存在
System.out.println(isCopy + " ：isCopy");
		if(isCopy <= 0){ 	//不存在，保存
			LoginInfo info = new LoginInfo();
			info.setUserName(userName);
			info.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
			info.setState(LoginInfo.STATE_NORMAL); //类名调用
			info.setUserType(LoginInfo.USER_CLIENT); //前台用户信息
			this.mapper.addUser(info);
			//修改注册流程,初始化账户信息和用户信息 
			Account account = new Account();
			account.setAccountId(info.getId());
			this.accountService.addAccount(account);
			UserInfo userInfo = new UserInfo();
			userInfo.setUserInfoId(info.getId());
			this.userInfoService.addUserInfo(userInfo);
			
System.out.println("添加 成果");
		}else{
			throw new RuntimeException("注册失败，该用户经存在！");
		}
	}

	
	@Override
	public LoginInfo loginUserInfo(String username, String password, String ip, int userType) {
		LoginInfo info = this.mapper.loginUserInfo(username, password, userType);
		//修改登录逻辑，不管是否登录成功，都会有登录日志
		IpLog log = new IpLog(); 
		log.setIpPath(ip);
		log.setLoginTime(new Date());
		log.setUserName(username);
		log.setUserType(userType);
System.out.println(info); //是否存在
		if(info != null){
			//将用户放入session
			UserContext.putCurrent(info);
			log.setState(IpLog.STATE_SUCESS);
		}else{
			log.setState(IpLog.STATE_FAULT);
		}
		this.ipLogService.addIpLog(log);
		return info;
	}
	
	/**
	 * 注册之添加用户
	 */
	@Override
	public int addUser(LoginInfo info) {
		return this.mapper.addUser(info);
	}

	@Override
	public int getUserIsCopy(String userName) {
		return this.mapper.getUserIsCopy(userName);
	}

	@Override
	public void initAdmin() {
		
		//查询是都有管理员
		int result = this.mapper.getAdminForCount(LoginInfo.USER_MANAGER);
		//如果没有则创建一个默认管理员
System.out.println("查重管理员： " + result);
		if(result == 0){
			LoginInfo admin = new LoginInfo();
			admin.setUserName(BidConst.DEFAULT_ACCOUNT); //设置账户
			admin.setPassword(DigestUtils.md5DigestAsHex(BidConst.DEFAULT_PASSWORD.getBytes())); //设置密码
			admin.setState(LoginInfo.STATE_NORMAL);
			admin.setUserType(LoginInfo.USER_MANAGER);
			this.mapper.addUser(admin);
		}
	}

}
