package com.alipay.util;

import java.util.UUID;
import com.alipay.api.domain.AlipayTradePayModel;

public class TSSimpleAliPay {
	
	/**
	 * 支付宝条形码扫码支付
	 * @param subject 商品信息简介（示例：支付宝条形码支付测试）
	 * @param totalAmount 订单总金额（示例String(100.00) ）
	 * @param auth_code 条形授权码（扫码支付授权码，设备读取用户支付宝中的条码或者二维码信息 ）
	 * 
	 * @return resultStr
	 * 		   String 字段
	 * */
	
		public static String AliBarcodePay(String authCode,String totalAmount,String subject){
	        AlipayTradePayModel model = new AlipayTradePayModel();
	        String resultStr=null;
	        model.setAuthCode(authCode);
	        model.setSubject(subject);
	        model.setTotalAmount(totalAmount);
	        model.setOutTradeNo(UUID.randomUUID().toString());
	        model.setScene("bar_code");
	        try {
	            resultStr = AlipayTradePay.tradePay(model);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return resultStr;
		}
		/*public static void main(String[] args) {
			AliBarcodePay("280256348921025690","10.00","蓝山-黄鹤楼");
		}*/
	
}
