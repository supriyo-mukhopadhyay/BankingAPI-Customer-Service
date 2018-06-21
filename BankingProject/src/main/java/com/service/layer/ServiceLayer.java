package com.service.layer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.layer.BankingDAOLayer;
import com.exception.InvalidAuthException;
import com.exception.InvalidUserException;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.pojo.UserBankDetails;

@Service
public class ServiceLayer {

	BankingDAOLayer dao;
	private Client client;
	private WebTarget target;

	@Autowired
	public void setDao(BankingDAOLayer dao) {
		this.dao = dao;
	}

	public void auth(String uid, String pwd) throws InvalidUserException, InvalidAuthException {

		String password = dao.getPassword(uid);

		if (password == null)
			throw new InvalidUserException("wrng uid");

		if (!password.equals(pwd))
			throw new InvalidAuthException("wrng pwd");
	}

	public UserBankDetails setUserBankDetails(int empid, int ifsc) throws Exception {
		
		List<Object> list = new ArrayList<Object>();
		
		String name = dao.getName(empid);
		String bankName = dao.getBankName(ifsc);
		int accNo = dao.getAccount(empid);
		int bal = dao.getBalance(empid);
		
		UserBankDetails bankDetails = new UserBankDetails();
		bankDetails.setAccountNumber(accNo);
		bankDetails.setBalance(bal);
		bankDetails.setBankName(bankName);
		bankDetails.setName(name);
		

		return bankDetails;
	}

	public String bankDetails(int ifsc) {
		return dao.getBankName(ifsc);
	}
	
	public int bankIfsc(String bankName)
	{
		return dao.getBankIfsc(bankName);
	}

	public void userRegistration(int ifsc,int accno, int balance, int uid) {
		
		ClientConfig clientConfig = new ClientConfig();
		client = ClientBuilder.newClient(clientConfig);
		target = client.target(getBaseURI());
		target.path("details").path(String.valueOf(ifsc)).path(String.valueOf(accno)).
		path(String.valueOf(balance)).path(String.valueOf(uid)).
		request().accept(MediaType.APPLICATION_JSON).get();
//		String userName = dao.getUserName(uid);
//		String bankName = dao.getBankName(ifsc);
//		dao.registerUser(bankName, balance, uid, userName);
	}

	public String getUserName(int empid) {
		return dao.getUserName(empid);
	}

	public void updateAccountDetails(String name, int balance) {
		ClientConfig clientConfig = new ClientConfig();
		client = ClientBuilder.newClient(clientConfig);
		target = client.target(getBaseURI());
		target.path("details").path(String.valueOf(name)).path(String.valueOf(balance)).
		request().accept(MediaType.APPLICATION_JSON).get();
	}

	public URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:3030/BankingAPI/rest/userService").build();

	}
	
	public void insertNewUser(String name, String pwd, int uid)
	{
		dao.newUser(name,uid,pwd);
	}
}
