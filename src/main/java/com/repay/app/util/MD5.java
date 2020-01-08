package com.repay.app.util;

import java.security.MessageDigest;

public class MD5 {
	
	//MD5加密
	public static String toMD5(String plainText) {
		StringBuffer buf = new StringBuffer("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte[] b = md.digest();
			for (int offset = 0; offset < b.length; offset++) {
				int i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}
		} catch (Exception e) {
		   e.printStackTrace();
		}
		  return buf.toString();
	 }
	
	/**
	 * 判断密码是否相等
	 * @param logPsw  登录密码
	 * @param md5Psw  md5密码
	 * */
	public static boolean isEquals(String logPsw,String md5Psw){
		boolean flag =  false;
		if(Help.isNull(logPsw)){
			return false;
		}
		if(Help.isNull(md5Psw)){
			return false;
		}
		if(toMD5(logPsw).equals(md5Psw)){
			flag = true;
		}else{
			flag = false;
		}
		return flag;
	}
	
	

}
