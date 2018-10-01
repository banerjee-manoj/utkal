package com.utkal.supply.jwt.utils;

import org.springframework.stereotype.Component;

@Component
public class PrivateKeySingleTon {
	
	private static PrivateKeySingleTon privateKey;
	private static String privateKeyString;
	
	private PrivateKeySingleTon(){
		
	}
	
	public static PrivateKeySingleTon getInstance(){
		if(privateKey == null){
		    privateKey = new PrivateKeySingleTon();
		}
		return privateKey;
	}

	public String getPrivateKeyString(){
		
		return this.privateKeyString;
	}
    public void setPrivateKeyString(String privateKey){
		
	   this.privateKeyString=privateKey;
	}
	
	
}
