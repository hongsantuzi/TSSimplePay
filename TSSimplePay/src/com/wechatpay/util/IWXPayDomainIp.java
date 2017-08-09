package com.wechatpay.util;

public class IWXPayDomainIp implements IWXPayDomain{
	
	private static DomainInfo instance =new  DomainInfo(WXPayConstants.DOMAIN_API,true);
	public DomainInfo returnInstance(){
		return instance;
	}
	
	@Override
	public void report(String domain, long elapsedTimeMillis, Exception ex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DomainInfo getDomain(WXPayConfig config) {
		// TODO Auto-generated method stub
		
		return returnInstance();
	}

}
