package com.fly.bos.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

	public static String getPassword(String pwd){
		try {
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] bs = digest.digest(pwd.getBytes());
			
			String password = "";
			for (byte b : bs) {
				int temp = b & 255;
				String hexString = Integer.toHexString(temp);
				if(temp >=0 && temp < 16){
					password = password +"0"+ hexString;
				}else{
					password = password + hexString;
				}
			}
			
			return password;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		} 
	}
	
	public static void main(String[] args) {
		System.out.println(MD5Utils.getPassword("abc"));
	}
}
