package com.alipay.util;

import java.util.Properties;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.PropertiesUtil.ProUtil;

public class InitAliPay {
		private static final String ProURL ="/alipay.properties";
		private static Properties prop;
		static {
			prop = ProUtil.pro(ProURL);
			
		}
	    public static AlipayClient alipayClient;
	    public static String charset = "UTF-8";
	    public static String privateKey = prop.getProperty("privateKey");
	    public static String alipayPulicKey = prop.getProperty("alipayPulicKey");
	    public static String serverUrl = prop.getProperty("serverUrl");
	    public static String appId = prop.getProperty("appId");
	    public static String format = "json";
	    public static String signType = "RSA2";
	    public static String notify_domain = prop.getProperty("notify_domain");
	    /*static int s =1;*/
	    static {
	        alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset, alipayPulicKey, signType);
	        /*System.out.println("=============初始化次数"+s+"次");*/
	        /*s++;*/
	    }   
}	
