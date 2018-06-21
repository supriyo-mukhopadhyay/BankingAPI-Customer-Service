package com.dao.layer;

import com.exception.InvalidUserException;
import java.lang.Thread.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class BankingDAOLayer {

	Connection conn = null;

	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
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
	private static final String USER_NAME = "SELECT userid FROM bankingschema.user_details WHERE user_name = ?";
	
	public String getPassword(String uid) throws SQLException, ClassNotFoundException {
		conn = DatabaseConnectivity.getConnectionUrl();
		try {
			// String pwd=template.queryForObject(GET_PASSWORD_QRY,
			// String.class, uid);
			String pwd = null;
			Statement st = conn.createStatement();
			preparedStatement = conn.prepareStatement(GET_PASSWORD_QRY);
			preparedStatement.setString(1, uid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				pwd = resultSet.getString("password");
			}
			conn.close();
			return pwd;

		} catch (Exception e) {	
			conn.close();
			throw new InvalidUserException("wrng uid");
		}

	}

	public String getName(int empid) throws Exception {
		String name = "";
		conn = DatabaseConnectivity.getConnectionUrl();
		try {
			Statement statement = conn.createStatement();
			preparedStatement = conn.prepareStatement(GET_NAME_QRY);
			preparedStatement.setInt(1, empid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				name = resultSet.getString("user_name");
			}
			conn.close();
			return name;
		} catch (Exception e) {
			e.printStackTrace();
			conn.close();
			throw new Exception();
		}

	}

	public int getAccount(int empid) throws Exception {
		int acc = 0;
		conn = DatabaseConnectivity.getConnectionUrl();
		try {
			Statement statement = conn.createStatement();
			preparedStatement = conn.prepareStatement(GET_ACCNO_QRY);
			preparedStatement.setInt(1, empid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				acc = resultSet.getInt("account_number");
			}
			conn.close();
			return acc;
		} catch (Exception e) {
			e.printStackTrace();
			conn.close();
			throw new Exception();
		}

	}

	public int getBalance(int empid) throws Exception {
		int bal = 0;
		conn = DatabaseConnectivity.getConnectionUrl();
		try {
			Statement statement = conn.createStatement();
			preparedStatement = conn.prepareStatement(GET_BALANCE_QRY);
			preparedStatement.setInt(1, empid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				bal = resultSet.getInt("balance");
			}
			conn.close();
			return bal;
		} catch (Exception e) {
			e.printStackTrace();
			conn.close();
			throw new Exception();
		}

	}

	public String getBankName(int ifsc) throws SQLException, ClassNotFoundException {
		String name = "";
		conn = DatabaseConnectivity.getConnectionUrl();
		Statement statement = conn.createStatement();
		preparedStatement = conn.prepareStatement(BANK_NAME);
		preparedStatement.setInt(1, ifsc);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			name = resultSet.getString("bank_name");
		}
		conn.close();
		return name;
	}
	
	public int getUid(String name) throws ClassNotFoundException, SQLException
	{
		int uid = 0;
		conn = DatabaseConnectivity.getConnectionUrl();
		Statement statement = conn.createStatement();
		preparedStatement = conn.prepareStatement(USER_NAME);
		preparedStatement.setString(1, name);
		resultSet = preparedStatement.executeQuery();
		while(resultSet.next())
		{
			uid = resultSet.getInt("UserId");
		}
		
		return uid;
	}
	
	public int getBankIfsc(String bankNm) throws ClassNotFoundException, SQLException
	{
		conn = DatabaseConnectivity.getConnectionUrl();
		Statement statement = conn.createStatement();
		preparedStatement = conn.prepareStatement(IFSC_CODE);
		preparedStatement.setString(1, bankNm);
		resultSet = preparedStatement.executeQuery();
		int ifsc = 0;
		while(resultSet.next())
		{
			ifsc = resultSet.getInt("ifsc_code");
		}
		
		return ifsc;
	}

	public String getUserName(int uid) throws SQLException, ClassNotFoundException {
		String name = "";
		conn = DatabaseConnectivity.getConnectionUrl();
		Statement statement = conn.createStatement();
		preparedStatement = conn.prepareStatement(GET_USER_NAME);
		preparedStatement.setInt(1, uid);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			name = resultSet.getString("user_name");
		}
		return name;
	}

	public void registerUser(String bankName, int accountNumber,int balance, int uid, String name)
			throws SQLException, ClassNotFoundException {
		conn = DatabaseConnectivity.getConnectionUrl();
		Statement statement = conn.createStatement();
		preparedStatement = conn.prepareStatement(REGISTRATION);
		preparedStatement.setString(1, bankName);
		preparedStatement.setInt(2,accountNumber);
		preparedStatement.setInt(3, balance);
		preparedStatement.setInt(4, uid);
		preparedStatement.setString(5, name);
		preparedStatement.execute();
		conn.close();
		

	}

	public void updateDetails(int empid, int balance) throws SQLException, ClassNotFoundException {
		conn = DatabaseConnectivity.getConnectionUrl();
		Statement statement = conn.createStatement();
		preparedStatement = conn.prepareStatement(UPDATE_ACCOUNT);
		preparedStatement.setInt(1, balance);
		preparedStatement.setInt(1, empid);
		preparedStatement.executeQuery();
		conn.close();
	}
	
	public int findUsers(int empid) throws SQLException, ClassNotFoundException
	{
		int count = 0;
		conn = DatabaseConnectivity.getConnectionUrl();
		Statement statement = conn.createStatement();
		preparedStatement = conn.prepareStatement(FIND_USER);
		preparedStatement.setInt(1, empid);
		resultSet = preparedStatement.executeQuery();
		while(resultSet.next())
		{
			count = resultSet.getFetchSize();
		}
		
		return count;
	}
	
}
