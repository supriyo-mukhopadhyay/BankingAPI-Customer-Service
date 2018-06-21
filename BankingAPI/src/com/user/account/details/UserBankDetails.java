package com.user.account.details;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement; 


@XmlRootElement(name = "user")
public class UserBankDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String name;
	public String bankName;
	public int accountNumber;
	public int balance;
	
	 public UserBankDetails(){}

	
	public String getName() {
		return name;
	}
	
	@XmlElement 
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBankName() {
		return bankName;
	}
	
	@XmlElement 
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	@XmlElement 
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getBalance() {
		return balance;
	}
	@XmlElement 
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	
}
