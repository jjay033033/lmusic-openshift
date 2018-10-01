package com.qq.weixin.mp.aes;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONObject;
import top.lmoon.util.ExceptionUtil;
import top.lmoon.util.HttpUtil;

public class WxLocalApi {
	
//	public static String getSHA1(String token, String timestamp, String nonce) throws AesException {
//		return SHA1.getSHA1(token, timestamp, nonce);
//	}
	
	public static boolean checkSignature(String token, String timestamp, String nonce, String signature) throws AesException {
		if(signature==null){
			return false;
		}
		return signature.equals(SHA1.getSHA1(token, timestamp, nonce));
	}
	
	private static String getAccessTokenResponse(String appid, String secret){
		String url = "https://api.weixin.qq.com/cgi-bin/token";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("grant_type", "client_credential");
		params.put("appid", appid);
		params.put("secret", secret);
		return HttpUtil.get(url, params );
	}
	
	public static String getAccessToken(String appid, String secret){
		String resp = getAccessTokenResponse(appid, secret);
		if(StringUtils.isBlank(resp)){
			return null;
		}
		JSONObject jo = JSONObject.fromObject(resp);
		String at = jo.getString("access_token");
		return at;
	}
	
	public static String encryptMsg(String token, String encodingAesKey, String appId, String replyMsg, String timestamp, String nonce){
		try {
			//
			// 第三方回复公众平台
			//

			// 需要加密的明文
//			String encodingAesKey = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFG";
//			String token = "pamtest";
//			String timestamp = "1409304348";
//			String nonce = "xxxxxx";
//			String appId = "wxb11529c136998cb6";
//			String replyMsg = " 中文<xml><ToUserName><![CDATA[oia2TjjewbmiOUlr6X-1crbLOvLw]]></ToUserName><FromUserName><![CDATA[gh_7f083739789a]]></FromUserName><CreateTime>1407743423</CreateTime><MsgType><![CDATA[video]]></MsgType><Video><MediaId><![CDATA[eYJ1MbwPRJtOvIEabaxHs7TX2D-HV71s79GUxqdUkjm6Gs2Ed1KF3ulAOA9H1xG0]]></MediaId><Title><![CDATA[testCallBackReplyVideo]]></Title><Description><![CDATA[testCallBackReplyVideo]]></Description></Video></xml>";

			WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
			String mw = pc.encryptMsg(replyMsg, timestamp, nonce);
			System.out.println("加密后: " + mw);
			return mw;
		} catch (AesException e) {
			ExceptionUtil.handleException("加密出错", e);
		}
		return null;
	}
	
	
//	public static void main(String[] args) {
//		String src = "{\"button\":[{\"type\":\"click\",\"name\":\"今日歌曲\",\"key\":\"V1001_TODAY_MUSIC\"},{\"name\":\"菜单\",\"sub_button\":[{\"type\":\"view\",\"name\":\"搜索\",\"url\":\"http://www.soso.com/\"},{\"type\":\"click\",\"name\":\"赞一下我们\",\"key\":\"V1001_GOOD\"}]}]}";
//		String url = "https://api.weixin.qq.com/cgi-bin/menu/create";
//		Map<String,Object> params = new HashMap<String, Object>();
//
//		String accessToken = getAccessToken(appid, secret);
//		System.out.println(accessToken);
//		params.put("access_token", accessToken );
//		
//		Map<String,Object> formParams = new HashMap<String, Object>();
//		formParams.put("body", src);
//		String post = HttpUtil.post(url, params, formParams);
////		System.out.println(post);
//
//	}
	
	

}
