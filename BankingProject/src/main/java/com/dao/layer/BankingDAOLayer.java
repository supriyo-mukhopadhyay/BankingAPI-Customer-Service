package com.dao.layer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.exception.InvalidUserException;

import java.sql.PreparedStatement;

import javax.sql.DataSource;

@Repository
public class BankingDAOLayer {

private JdbcTemplate template;
//	private DataSource dataSource;
//	
//	public void setDataSource(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	private static final String GET_PASSWORD_QRY = "SELECT Password FROM bankingschema.user_details WHERE UserId=?";
	private static final String GET_NAME_QRY = "SELECT user_name FROM bankingschema.user_details WHERE UserId=?";
	private static final String GET_BALANCE_QRY = "SELECT balance FROM bankingschema.user_account_details WHERE UserId=?";
	private static final String GET_ACCNO_QRY = "SELECT account_number FROM bankingschema.user_account_details WHERE UserId=?";
	private static final String REGISTRATION = "INSERT INTO bankingschema.user_account_details VALUES (?,?,?,?,?)";
	private static final String UPDATE_ACCOUNT = "UPDATE bankingschema.user_account_details SET balance = ? WHERE UserId=? ";
	private static final String BANK_NAME = "SELECT bank_name FROM bankingschema.bank_details WHERE ifsc_code = ?";
	private static final String GET_USER_NAME = "SELECT User_name FROM bankingschema.user_details WHERE userid = ?";
	private static final String FIND_USER = "SELECT count(*) FROM bankingschema.user_account_details WHERE userid = ?";
	private static final String IFSC_CODE = "SELECT ifsc_code FROM bankingschema.bank_details WHERE bank_name = ?";
	//private static final String NEW_USER = "INSERT INTO bankingschema.user_details (User) VALUES (?,?,?)";
	
	public void newUser(String name, int uid, String pwd)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO bankingschema.user_details (UserId,User_Name,Password) VALUES (");
		builder.append(uid);
		builder.append(",").append("'").append(name).append("'").append(",").append("'");
		builder.append(pwd).append("'").append(")");		
		template.execute(builder.toString());
	}
	
	public String getPassword(String uid) {
		
		try{
			String pwd=template.queryForObject(GET_PASSWORD_QRY, String.class, uid);
			return pwd;
		}catch(EmptyResultDataAccessException e){
			throw new InvalidUserException("wrng uid");
		}
		
	}
	
	public String getName(int empid) throws Exception
	{
		try
		{
			String name = template.queryForObject(GET_NAME_QRY, String.class,empid);
			return name;
		}
		catch(EmptyResultDataAccessException e)
		{
			e.printStackTrace();
			throw new Exception();
		}
		
	}
	
	public int getAccount(int empid) throws Exception
	{
		try
		{
			int acc = template.queryForObject(GET_ACCNO_QRY, Integer.class,empid);
			return acc;
		}
		catch(EmptyResultDataAccessException e)
		{
			e.printStackTrace();
			throw new Exception();
		}
		
	}
	
	public int getBalance(int empid) throws Exception
	{
		try
		{
			int bal = template.queryForObject(GET_BALANCE_QRY, Integer.class,empid);
			return bal;
		}
		catch(EmptyResultDataAccessException e)
		{
			e.printStackTrace();
			throw new Exception();
		}
		
	}
	
	public String getBankName(int ifsc)
	{
		String name = template.queryForObject(BANK_NAME, String.class,ifsc);
		return name;
	}
	
	public int getBankIfsc(String bankNm)
	{
		int ifsc = template.queryForObject(IFSC_CODE, Integer.class,bankNm);
		return ifsc;
	}
	
	public String getUserName(int uid)
	{
		String name =""; 
		name = template.queryForObject(GET_USER_NAME, String.class,uid);
		return name;
	}
	
	public void registerUser(String bankName, int balance,int uid,String name)
	{
		template.queryForObject(REGISTRATION, String.class,bankName,balance,uid,name);
	}
	
	public void updateDetails(int empid, int balance)
	{
		template.queryForObject(UPDATE_ACCOUNT, String.class,balance,empid);
	}
}
