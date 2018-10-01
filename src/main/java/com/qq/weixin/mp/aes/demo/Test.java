package com.qq.weixin.mp.aes.demo;

import java.util.ArrayList;
import java.util.List;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WxLocalApi;

public class Test {
	
//	--signature:0e6873d67237eafea8d111705d4fea993bd9e269
//	--timestamp:1538371082
//	--nonce:11870657
//	--echostr:1038784520321744430

	public static void main(String[] args) {
		String signature = "0e6873d67237eafea8d111705d4fea993bd9e269";
		String timestamp = "1538371082";
		String nonce = "11870657";
		String token = "lmoonmusic";
		List<String> list = new ArrayList<String>();
		list.add(token);
		list.add(nonce);
		list.add(timestamp);
		boolean sha1;
		try {
			sha1 = WxLocalApi.checkSignature(token, timestamp, nonce, signature);
			System.out.println(sha1);
		} catch (AesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
