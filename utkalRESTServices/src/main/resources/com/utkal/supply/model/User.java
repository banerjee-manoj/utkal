package com.utkal.supply.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown=true)
@XmlRootElement
public class User {
	
	
	private String userName;
	private String password;
	private boolean isValidUser;
	
	
	
	/* (non-Javadoc)
	 * @see com.utkal.supply.model.UserInterface#getUserName()
	 */
	public String getUserName() {
		return userName;
	}
	/* (non-Javadoc)
	 * @see com.utkal.supply.model.UserInterface#setUserName(java.lang.String)
	 */
	public void setUserName(String usreName) {
		this.userName = usreName;
	}
	/* (non-Javadoc)
	 * @see com.utkal.supply.model.UserInterface#getPassword()
	 */
	public String getPassword() {
		return password;
	}
	/* (non-Javadoc)
	 * @see com.utkal.supply.model.UserInterface#setPassword(java.lang.String)
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/*
	 * 
	 */
	public boolean isValidUser() {
		return isValidUser;
	}
	
	/*
	 * 
	 * 
	 */
	public void setValidUser(boolean isValidUser) {
		this.isValidUser = isValidUser;
	}
	
	

}
