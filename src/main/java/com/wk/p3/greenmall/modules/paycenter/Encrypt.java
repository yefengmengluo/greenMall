package com.wk.p3.greenmall.modules.paycenter;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * @author Robin
 * 加密
 *
 */
public class Encrypt {
	private static final String sign="99114pay";
	private static final String key="99114paykey";
	public static String encry(String str){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return Base64.encodeBase64String(md.digest((str+sign).getBytes()));
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String key(String str){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return Base64.encodeBase64String(md.digest((str+key).getBytes()));
		} catch (Exception e) {
			return null;
		}
	}

}
