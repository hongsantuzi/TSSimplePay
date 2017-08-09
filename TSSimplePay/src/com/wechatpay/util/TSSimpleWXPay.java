package com.wechatpay.util;

import java.util.HashMap;
import java.util.Map;

public class TSSimpleWXPay {
	private static WXpayMessage wXpayMessage;
	private static WXPay wXPay;
	static{
		wXpayMessage=WXpayMessage.GetInstance();
		try {
			wXPay=new WXPay(wXpayMessage,true,false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 条形码扫码支付
	 * @param body 商品信息简介（格式：蓝山-彼得龙）
	 * @param total_fee 订单总金额（订单总金额，单位为分，只能为整数 ）
	 * @param auth_code 条形授权码（扫码支付授权码，设备读取用户微信中的条码或者二维码信息 ）
	 * @param spbill_create_ip 本次调用机器IP地址（传入String(null)则为默认值：127.0.0.1）
	 * 
	 * @return Map<String,String> BarCodeMap
	 * 		   将XML处理为MAP<String,String> 具体返回值详情
	 * 		 https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_10&index=1
	 * */
	public static Map<String,String> WeChatBarcodePay(String body,String total_fee,String auth_code,String spbill_create_ip){
		Map<String,String> BarCodeMap = null;
		try {
			//实例化工具对象，详情查看WXPay.java
			Map<String,String> reqData=new HashMap<String, String>();
			reqData.put("body", body);
			reqData.put("out_trade_no", WXPayUtil.generateUUID());
			reqData.put("auth_code", auth_code);
			reqData.put("total_fee", total_fee);
			if(spbill_create_ip.equals("null")){
				reqData.put("spbill_create_ip", "127.0.0.1");
			}else{
				reqData.put("spbill_create_ip", spbill_create_ip);
			}
			BarCodeMap=wXPay.microPay(reqData);
			 /*for (String key : BarCodeMap.keySet()) {
				   System.out.println("key= "+ key + " and value= " + BarCodeMap.get(key));
				  }*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*System.out.println(WXPayUtil.generateNonceStr());*/
		return BarCodeMap;
	}
	
	/**
	 * 统一下单   通用于公众号支付，（H5支付略有不同）
	 * 
	 * @param body 商品信息简介（格式：蓝山-彼得龙）
	 * @param total_fee 订单总金额（订单总金额，单位为分，只能为整数 ）
	 * @param openid 条形授权码（用户标识信息，获取参数详情，参见WeChatOAuth2类 ）
	 * @param spbill_create_ip 本次调用机器IP地址（传入String(null)则为默认值：127.0.0.1）
	 * 
	 * @return Map<String,String> BarCodeMap
	 * 		   将XML处理为MAP<String,String> 具体返回值详情
	 * 		 https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_11&index=3
	 * */
	public static Map<String,String> UnifiedOrder(String body,String total_fee,String spbill_create_ip,String openid){
		Map<String,String> UnifiedOrderMap = null;
		Map<String,String> reqData=new HashMap<String, String>();
		reqData.put("body", body);
		reqData.put("out_trade_no", WXPayUtil.generateUUID());
		reqData.put("total_fee", total_fee);
		reqData.put("notify_url", wXpayMessage.getnotify_url());
		reqData.put("trade_type", "JSAPI");
		reqData.put("openid",openid);
		if(spbill_create_ip.equals("null")){
			reqData.put("spbill_create_ip", "127.0.0.1");
		}else{
			reqData.put("spbill_create_ip", spbill_create_ip);
		}
		try {
			UnifiedOrderMap=wXPay.unifiedOrder(reqData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return UnifiedOrderMap;
	}
	
	/*public static Map<String,String> WeChats
	public static void main(String[] args) {
		WeChatBarcodePay("蓝山-风云会","1","130364212419719051","null");
	}*/
}
