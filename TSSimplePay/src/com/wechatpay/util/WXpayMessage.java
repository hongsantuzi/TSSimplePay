package com.wechatpay.util;

import java.io.InputStream;
import java.util.Properties;

import com.PropertiesUtil.ProUtil;

public class WXpayMessage extends WXPayConfig{

	private static final String ProURL="/wechat.properties";
	
	private static Properties prop;
	
	static {
		prop = ProUtil.pro(ProURL);
		
	}
	
	@Override
	String getAppID() {
		// TODO Auto-generated method stub
		return prop.getProperty("APP_ID");
	}

	@Override
	String getMchID() {
		// TODO Auto-generated method stub
		return prop.getProperty("MCH_ID");
	}

	@Override
	String getKey() {
		// TODO Auto-generated method stub
		return prop.getProperty("KEY");
	}
	
	@Override
	InputStream getCertStream() {
		// TODO Auto-generated method stub
		// 暂无商户证书
		return null;
	}

	@Override
	IWXPayDomain getWXPayDomain() {
		// TODO Auto-generated method stub
		// 域名与IP调用
		IWXPayDomain iWXPayDomain =(IWXPayDomain) new IWXPayDomainIp();
		
		return iWXPayDomain ;
	}
	
	private static WXpayMessage instance = new WXpayMessage();
	private WXpayMessage(){};
	public static WXpayMessage GetInstance(){
		return instance;
		
	}

	@Override
	String getAppSecret() {
		// TODO Auto-generated method stub
		return prop.getProperty("AppSecret");
	}

	@Override
	String getREDIRECT_URI() {
		// TODO Auto-generated method stub
		return prop.getProperty("REDIRECT_URI");
	}

	@Override
	String getnotify_url() {
		// TODO Auto-generated method stub
		return prop.getProperty("notify_url");
	}

}
