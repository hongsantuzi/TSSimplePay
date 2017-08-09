package com.alipay.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradePayResponse;

public class AlipayTradePay extends InitAliPay{
	/**
     * 条形码支付
     * https://doc.open.alipay.com/docs/doc.htm?spm=a219a.7629140.0.0.Yhpibd&treeId=194&articleId=105170&docType=1#s4
     * @throws AlipayApiException
     */
    public static String tradePay(AlipayTradePayModel model) throws AlipayApiException {
        AlipayTradePayResponse response = tradePayToResponse(model);
        return response.getBody();
    }

    public static AlipayTradePayResponse tradePayToResponse(AlipayTradePayModel model) throws AlipayApiException{
        AlipayTradePayRequest request = new AlipayTradePayRequest();
        request.setBizModel(model);// 填充业务参数
        return alipayClient.execute(request); // 通过alipayClient调用API，获得对应的response类
    }
}
