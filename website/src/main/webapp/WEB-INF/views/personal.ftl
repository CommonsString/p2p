<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>个人 中心</title>
		<#include "common/links-tpl.ftl" />
		<script type="text/javascript" src="/js/plugins/jquery.form.js"></script>
		<link type="text/css" rel="stylesheet" href="/css/account.css" />
		
		<script type="text/javascript">
			$(function(){
				if($('#bindPhoneModal').size() > 0){
					
					$('#btn_showBindPhone').click(function(){
						$('#bindPhoneModal').modal('show');
					});
					
					//给发送按钮绑定时间
					$('#sendVerifyCode').click(function(){
						var _this = $(this); //按钮$('#sendVerifyCode')
						_this.attr('disabled', true); //点击即禁用，防止重复请求
						//发送一个ajax请求
						$.ajax({
							url : '/sendVerifyCode.do', 
							dataType : 'json', 
							type : 'POST', 
							data : {
								phoneNumber : $('#phoneNumber').val()
							}, 
							success : function(data){
								console.log('success', data.success);
								
								if(data.success){ 	//如果请求成功，给提示信息
									var sec = 60; //秒
									var time = window.setInterval(function(){
										sec--;
										if(sec > 0){
											_this.text(sec + '秒重新发送');
										}else{
											window.clearInterval(time); //去掉定时器
											_this.text('重新发送验证码');
											_this.attr('disabled', false);
										}
									}, 1000);
								}else{
									//请求失败，禁用按钮，开启倒计时
									$.messager.popup(data.msg); 
									_this.attr('disabled', false);
								}
							}
						});
					});
					
					//将表单变成ajaxForm
					$('#bindPhoneForm').ajaxForm(function(data){
						if(data.success){
							window.location.reload(); //重新加载页面
						}else{
							$.messager.popup(data.msg);
						}
					});
					
					//给保存按钮绑定事件
					$("#bindPhone").click(function(){
						$('#bindPhoneForm').submit();
					});
					
					
				}
			});
			
		</script>
	</head>
	<body>
		<!-- 网页顶部导航 -->
		<#include "common/head-tpl.ftl" />
		<!-- 网页导航 -->
		<!-- assign 在当前的freemarker上下文中，添加一个变量，变量的名为cerrentNav 值为account -->
		<#assign currentNav="account" />
		<!-- 在包含的文件中也可以使用该变量 -->
		<#include "common/navbar-tpl.ftl" /> 
		
		<div class="container">
			<div class="row">
				<!--导航菜单-->
				<div class="col-sm-3">
					<#assign currentMenu="account" />
					 <#include "common/leftmenu-tpl.ftl" />
				</div>
				<!-- 功能页面 -->
				<div class="col-sm-9">
					<div class="panel panel-default">
						<div class="panel-body el-account">
							<div class="el-account-info">
								<div class="pull-left el-head-img">
									<img class="icon" src="images/person_icon.png" />
								</div>
								<div class="pull-left el-head">
									<p>用户名 ：${logininfo.userName}</p>
									<p>最后登录时间：2015-01-25 15:30:10</p>
								</div>
								<div class="pull-left" style="text-align: center;width: 400px;margin:30px auto 0px auto;">
									<a class="btn btn-primary btn-lg" href="/recharge.do">账户充值</a>
									<a class="btn btn-danger btn-lg" href="#">账户提现</a>
								</div>
								<div class="clearfix"></div>
							</div>
							
							<div class="row h4 account-info">
								<div class="col-sm-4">
									账户总额：<span class="text-primary">${account.sumMoney}元</span>
								</div>
								<div class="col-sm-4">
									可用金额：<span class="text-primary">${account.usableAmount}元</span>	
								</div>
								<div class="col-sm-4">
									冻结金额：<span class="text-primary">${account.freezedAmount}元</span>
								</div>
							</div>
							
							<div class="row h4 account-info">
								<div class="col-sm-4">
									待收利息：<span class="text-primary">${account.unReceiveInterest}元</span>
								</div>
								<div class="col-sm-4">
									待收本金：<span class="text-primary">${account.unReceivePrincipal}元</span>	
								</div>
								<div class="col-sm-4">
									待还本息：<span class="text-primary">${account.unReturnAmount}元</span>
								</div>
							</div>
							
							<div class="el-account-info top-margin">
								<div class="row">
									<div class="col-sm-4">
										<div class="el-accoun-auth">
											<div class="el-accoun-auth-left">
												<img src="images/shiming.png" />
											</div>
											<div class="el-accoun-auth-right">
												<h5>实名认证</h5>
												<p>
													已认证
													<a href="/realAuth.do">查看</a>
												</p>
												<h5>实名认证</h5>
												<p>
													未认证
													<a href="/realAuth.do">立刻认证</a>
												</p>
											</div>
											<div class="clearfix"></div>
											<p class="info">实名认证之后才能在平台投资</p>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="el-accoun-auth">
											<div class="el-accoun-auth-left">
												<img src="images/shouji.jpg" />
											</div>
											<div class="el-accoun-auth-right">
												<#if userinfo.isBindPhone>
												<h5>手机认证</h5>
												<p>
													已认证
													<a href="#">查看</a>
												</p>
												<#else>
												<h5>手机认证</h5>
												<p>
													未认证
													<a id="btn_showBindPhone" href="javascript:;">立刻认证</a>
												</p>
												</#if>
											</div>
											<div class="clearfix"></div>
											<p class="info">可以收到系统操作信息,并增加使用安全性</p>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="el-accoun-auth">
											<div class="el-accoun-auth-left">
												<img src="images/youxiang.jpg" />
											</div>
											<div class="el-accoun-auth-right">
												<h5>邮箱认证</h5>
												<p>
													已绑定
													<a href="#">查看</a>
												</p>
												<h5>邮箱认证</h5>
												<p>
													未绑定
													<a id="btn_showBindEmail" href="javascript:;">立刻绑定</a>
												</p>
											</div>
											<div class="clearfix"></div>
											<p class="info">您可以设置邮箱来接收重要信息</p>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-4">
										<div class="el-accoun-auth">
											<div class="el-accoun-auth-left">
												<img src="images/baozhan.jpg" />
											</div>
											<div class="el-accoun-auth-right">
												<h5>VIP会员</h5>
												<p>
													普通用户
													<a href="#">查看</a>
												</p>
											</div>
											<div class="clearfix"></div>
											<p class="info">VIP会员，让你更快捷的投资</p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>		
		
		
		<!-- 绑定手机模式窗口 -->
		<!-- 没有绑定是可以弹出 -->
		<#if !userinfo.isBindPhone>	
		<div class="modal fade" id="bindPhoneModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title" id="exampleModalLabel">绑定手机</h4>
		      </div>
		      <div class="modal-body">
				<form class="form-horizontal" id="bindPhoneForm" method="post" action="/bindPhone.do">
					<div class="form-group">
						    <label for="phoneNumber" class="col-sm-2 control-label">手机号:</label>
						    <div class="col-sm-4">
						      <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" />
						      <button id="sendVerifyCode" class="btn btn-primary" type="button">发送验证码</button>
						    </div>
						</div>
						<div class="form-group">
						    <label for="verifyCode" class="col-sm-2 control-label">验证码:</label>
						    <div class="col-sm-4">
						      <input type="text" class="form-control" id="verifyCode" name="verifyCode" />
						    </div>
						</div>
				</form>
		      </div>
		      <div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary" id="bindPhone">保存</button>
		      </div>
		    </div>
		  </div>
		</div>
		</#if>
		
		<div class="modal fade" id="bindEmailModal" tabindex="-1" role="dialog" aria-labelledby="bindEmailModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title" id="bindEmailModalLabel">绑定邮箱</h4>
		      </div>
		      <div class="modal-body">
			<form class="form-horizontal" id="bindEmailForm" method="post" action="/bindEmail.do">
				<div class="form-group">
					    <label for="email" class="col-sm-2 control-label">邮箱地址:</label>
					    <div class="col-sm-6">
					      <input type="text" class="form-control" id="email" name="email" />
					    </div>
					</div>
			</form>
		      </div>
		      <div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			<button type="button" class="btn btn-primary" id="bindEmail">发送验证邮件</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		
		 <#include "common/footer-tpl.ftl" /> 
	</body>
</html>