package com.pojo;

import javax.xml.bind.annotation.XmlRootElement; 
import javax.xml.bind.annotation.XmlElement; 

@SuppressWarnings("restriction")
@XmlRootElement(name = "user")
public class UserBankDetails {

	public String name;
	public String bankName;
	public int accountNumber;
	public int balance;
	
	
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
