package com.service.layer;

import java.sql.SQLException;
import com.dao.layer.BankingDAOLayer;
import com.exception.InvalidAuthException;
import com.exception.InvalidUserException;
import com.user.account.details.UserBankDetails;

public class BankingService {

	private BankingDAOLayer dao = new BankingDAOLayer();
	
	public void auth(String uid, String pwd) throws InvalidUserException,
	InvalidAuthException, SQLException, ClassNotFoundException {

		String password=dao.getPassword(uid);
		
		if(password==null)
			throw new InvalidUserException("wrng uid");
		
		if(!password.equals(pwd))
			throw new InvalidAuthException("wrng pwd");
		}
	
	
	public UserBankDetails getUserBankDetails(int empid, int ifsc) throws Exception
	{
	
		UserBankDetails dtUser = new UserBankDetails();		
		dtUser.setName(dao.getName(empid));
		dtUser.setAccountNumber(dao.getAccount(empid));
		dtUser.setBalance(dao.getBalance(empid));
		dtUser.setBankName(dao.getBankName(ifsc));
		return dtUser;
	}
	
	public String bankDetails(int ifsc) throws SQLException, ClassNotFoundException
	{
		return dao.getBankName(ifsc);
	}
	
	public void userBankRegistration(int ifsc,int accountNumber, int balance, int uid) throws SQLException, ClassNotFoundException
	{
		String userName = dao.getUserName(uid);
		String bankName = dao.getBankName(ifsc);
		dao.registerUser(bankName,accountNumber, balance,uid,userName);
		//return result;
	}
	
	public String getUserName(int empid) throws SQLException, ClassNotFoundException
	{
		return dao.getUserName(empid);
	}
	
	public int findUserinBankDetails(int empid) throws ClassNotFoundException, SQLException
	{
		return dao.findUsers(empid);
	}
	
	public void updateAccountDetails(String name, int balance) throws SQLException, ClassNotFoundException
	{
		int empid = dao.getUid(name);
		dao.updateDetails(empid, balance);
	}
	
	public int bankIfsc(String bankName) throws ClassNotFoundException, SQLException
	{
		return dao.getBankIfsc(bankName);
	}
	
	public int getUid(String name) throws ClassNotFoundException, SQLException
	{
		return dao.getUid(name);
	}
}
