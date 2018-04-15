package com.base.service.Impl;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.base.service.VerifyCodeService;
import com.base.tools.BidConst;
import com.base.tools.DateUtil;
import com.base.tools.UserContext;
import com.base.verifycode.VerifyCodeVO;
import com.base.verifycode.vCodeUtils;


@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {
	
	@Value("${sms.username}")
	private String username;
	
	@Value("${sms.password}")
	private String password;
	
	@Value("${sms.apikey}")
	private String apikey;
	
	@Value("${url}")
	private String url;
	

	@Override
	public void sendVerifyCodeForPhone(String phoneNumber) {
System.out.println(username + " : " + password);
		VerifyCodeVO vc = UserContext.getCurrentVerifyCode();
		if(vc == null || DateUtil.secondsBetween(new Date(), vc.getLastSendTime()) > 90){ 
			//生成验证码，
			String verifyCode = vCodeUtils.getRandomNum();
System.out.println("手机号：" + phoneNumber + "  验证码： " + verifyCode);
			//发送短信
			try {
				URL httpUrl = new URL(this.url); //创建一个url
				HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection(); //通过url得到一个httpURLConnect对象
				//拼接POST请求内容
				StringBuilder context = new StringBuilder();
				 context
				.append("username=").append(this.username)
				.append("&password=").append(this.password)
				.append("&apikey=").append(this.apikey)
				.append("&mobile=").append(phoneNumber)
				.append("&content=").append("验证码是:")
				.append(verifyCode).append(",五分钟内有效！");  
				 
System.out.println(context.toString());
				
				conn.setRequestMethod("POST"); //发送POST请求，大写
				conn.setDoOutput(true);  //设置该请求是有请求体
				conn.getOutputStream().write(context.toString().getBytes()); //写入请求体
				//获取响应流(响应流其实，已经发送请求)
				String response = StreamUtils.copyToString(conn.getInputStream(), Charset.forName("UTF-8"));
				if(response.startsWith("success")){ //响应成功
System.out.println("响应成功！");
					VerifyCodeVO vo = new VerifyCodeVO(verifyCode, phoneNumber, new Date());
					UserContext.setVerifyCode(vo);
					
				}else{  //响应失败
					System.out.println("响应失败！");
					throw new RuntimeException();  //在外层catch被拦截
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("短信发送失败！"); //拦截68行
			} 
			//把手机号 ，验证码。装配到vc中，保存在session
			vc = new VerifyCodeVO();
			vc.setPhoneNumber(phoneNumber);
			vc.setVerifyCode(verifyCode);
			vc.setLastSendTime(new Date());
			UserContext.setVerifyCode(vc); //放到session
		}else{
			throw new RuntimeException("发送过于频繁");
		}
	}

	
	/**
	 * 检验验证码的合法性
	 * 校正  session的验证码，和传上来的验证码和电话号码
	 */
	@Override
	public boolean validate(String phoneNumber, String verifyCode) {
		//得到验证码
		VerifyCodeVO vc = UserContext.getCurrentVerifyCode();
System.out.println("Session里的验证码：  " + vc);
		if(vc != null  //发送了验证码
				&& vc.getPhoneNumber().equals(phoneNumber)  //手机号相同
				&& vc.getVerifyCode().equalsIgnoreCase(verifyCode)//验证码相同
				&& DateUtil.secondsBetween(new Date(), vc.getLastSendTime()) <= BidConst.VEIFITY_CODE_LIMIT_TIME){ //验证码再有效期范围 
			return true;
		}
		return false;
	}
	
	

}
