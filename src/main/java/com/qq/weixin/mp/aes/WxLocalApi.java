package com.qq.weixin.mp.aes;

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

}
