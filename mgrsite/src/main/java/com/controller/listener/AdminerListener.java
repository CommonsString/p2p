package com.controller.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.base.service.LoginInfoService;


/**
 *  实现ApplicationListener即监听所有spring的消息。
 *  ApplicationEvent是spring的所有消息事件，是所有事件的父类
 *  如果监听器继承了ApplicationListener<ApplicationEvent>,即监听spring里面的所有消息
 *  @Component告诉spring
 */
@Component
public class AdminerListener implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	private LoginInfoService loginInfoService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) { //监听容器刷新事件
		this.loginInfoService.initAdmin();
	}
	
}
