package com.wechatpay.util;

import net.sf.json.JSONObject;  

public class WeChatOAuth2 {
		//微信支付信息对象
		private WXPayConfig config;
		
		public WeChatOAuth2(final WXPayConfig config) throws Exception {
			this.config=config;
		}
		//换取CODE的URL
		private static final String Code_Url ="https://open.weixin.qq.com/connect/qrconnect?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_login&state=STATE#wechat_redirect";
		/**
		 * 用户授权获取code
		 * 
		 * @return state 
		 * 	
		 * 用于保持请求和回调的状态，授权请求后原样带回给第三方。
		 * 该参数可用于防止csrf攻击（跨站请求伪造攻击），
		 * 建议第三方带上该参数，可设置为简单的随机数加session进行校验
		 * 
		 * 请求会定向到REDIRECT_URI   ？ code=code & state=state
		 * */
		public String getCode(){
			String state =WXPayUtil.generateUUID();
			String RequestCode = Code_Url.replace("APPID",config.getAppID()).replace("REDIRECT_URI", config.getREDIRECT_URI()).replace("STATE", state);
			WeixinUtil.httpRequest(RequestCode, "GET", null);
			return state;
		}
		
		//使用code获取access_token与用户唯一标识（OPENID）的URL
		private static final String access_token_Url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		/**
		 * 使用code换取access_token
		 * 
		 * @return JSONObject
		 * 
		 * 包含
		 *  "access_token":"ACCESS_TOKEN", 
		 *  "expires_in":access_token有效时间（7200两个小时）, 
		 *	"refresh_token":"用做刷新access_token的凭证",
		 *	"openid":"用户公众号唯一标识", 
		 *	"scope":"用户授权的作用域（暂且无视）",
		 *	"unionid": "当且仅当该网站应用已获得该用户的userinfo授权时，才会出现该字段，是真正一个微信公众号的唯一标识"
		 * */
		
		public  JSONObject getAccess_Token(String code){
			String RequestAccess_Token = access_token_Url.replace("APPID", config.getAppID()).replace("SECRET", config.getAppSecret()).replace("CODE", code);
			JSONObject jSONObject=WeixinUtil.httpRequest(RequestAccess_Token, "GET", null);
			return jSONObject;
		}
		
		//使用refresh_token刷新Access_token的URL
		private static final String refresh_token_Url="https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
		/**
		 * 使用refresh_token刷新Access_token
		 * Access_token是有效期的，可使用定时任务
		 * 
		 * @return JSONObject
		 *  "access_token":"ACCESS_TOKEN", 
		 *  "expires_in":超时时间, 
		 *	"refresh_token":"REFRESH_TOKEN", 
		 *	"openid":"OPENID", 
		 *	"scope":"SCOPE" 
		 *
		 * */
		
		public JSONObject onRefresh_Token(String refresh_token){
			String RequestRefresh_Token = refresh_token_Url.replace("APPID", config.getAppID()).replace("REFRESH_TOKEN", refresh_token);
			JSONObject jSONObject = WeixinUtil.httpRequest(RequestRefresh_Token, "GET", null);
			return jSONObject;
		}
}
