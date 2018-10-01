package com.utkal.supply.jwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SigningKeyResolver;
import io.jsonwebtoken.impl.crypto.JwtSigner;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.glassfish.jersey.internal.util.Base64;

public class JWTTokenCreator {
	
	
	public static String keyValue;	
	public static String getToken(String privateKey){
        SignatureAlgorithm signatureAlgo = SignatureAlgorithm.HS256;
        long nowmillis = System.currentTimeMillis();
        Date now = new Date(nowmillis);
   
        String encodedKey = Base64.encodeAsString(privateKey);//getEncoder().encodeToString(secretKey.getEncoded());
        
        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(encodedKey);//apiKey.getSecret());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgo.getJcaName());
     
         
        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId("123")
                                    .setIssuedAt(now)
                                    .setSubject("subject")
                                    .setIssuer("manoj").claim("Admin", "true").claim("createAccount", "true")
                                    .signWith(signatureAlgo, signingKey);
     
        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
		
	}

	
	public static boolean validateToken(String privateKey,String token){
			
		    //This line will throw an exception if it is not a signed JWS (as expected)
		    Claims claims = Jwts.parser()         
		       .setSigningKey(DatatypeConverter.parseBase64Binary(privateKey))
		       .parseClaimsJws(token).getBody();
		    System.out.println("ID: " + claims.getId());
		    if(null != claims.getId()){
		    	   System.out.println("Subject: " + claims.getSubject());
				    System.out.println("Issuer: " + claims.getIssuer());
		    	System.out.println("ValidToken");
		    	return true;
		    }else return false;
		 

		
	}
	
	
	
	public static void main(String args[]){
		
		String token = createToken();
		 //= "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjMiLCJpYXQiOjE1MDAxMDcwNDAsInN1YiI6InN1YmplY3QiLCJpc3MiOiJtYW5vaiIsIkFkbWluIjoidHJ1ZSIsImNyZWF0ZUFjY291bnQiOiJ0cnVlIn0.veIBcVoJ1u-mR7n1FdJtTeqGcgxNsbSO2TcHBIlJMGM";
		System.out.println(token);
		parseJWT(token);
		//validateToken("1234", token);
		
	}
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	public static String createToken(){
		
        SignatureAlgorithm signatureAlgo = SignatureAlgorithm.HS256;
        long nowmillis = System.currentTimeMillis();
        Date now = new Date(nowmillis);
        
        
     // create new key
        SecretKey secretKey = null;
        try {
        secretKey = KeyGenerator.getInstance("AES").generateKey();
        } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
        }
        System.out.println(secretKey.getAlgorithm()+"  "+secretKey.toString());
        // get base64 encoded version of the key
        String encodedKey = Base64.encodeAsString(secretKey.toString());//getEncoder().encodeToString(secretKey.getEncoded());
        keyValue=encodedKey;
        
        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(encodedKey);//apiKey.getSecret());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgo.getJcaName());
     
        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId("123")
                                    .setIssuedAt(now)
                                    .setSubject("subject")
                                    .setIssuer("manoj")
                                    .signWith(signatureAlgo, signingKey);
        
     
        //if it has been specified, let's add the expiration
       /* if (ttlMillis >= 0) {
        long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
     */
        //Builds the JWT and serializes it to a compact, URL-safe string
        System.out.println(builder.compact());
        return builder.compact();
	}
	
	
	public static void parseJWT(String jwt) {
		
	    //This line will throw an exception if it is not a signed JWS (as expected)
	    Claims claims = Jwts.parser()         
	       .setSigningKey(DatatypeConverter.parseBase64Binary(keyValue))
	       .parseClaimsJws(jwt).getBody();
	    System.out.println("ID: " + claims.getId());
	    System.out.println("Subject: " + claims.getSubject());
	    System.out.println("Issuer: " + claims.getIssuer());
	//    System.out.println("Expiration: " + claims.getExpiration());
	}

	
	/////////////////////////////////////////////////////////////////////////////////
	
}
