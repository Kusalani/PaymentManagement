package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrodb", "root", "");
			System.out.println("Connection successfully established");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	//read
	public String readPayment()  
	{   
		String output = ""; 
	
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				System.out.println("Error while connecting to the database for reading.");
			} 
			
			String query = "select * from payment"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			
			// Prepare the html table to be displayed    
			output = "<table class='table' border='1'><thead class='table-dark'>"
					+ "<th>Customer ID</th>"
					+ "<th>TEL No</th>"
					+ "<th>Date</th>"
					+ "<th>Amount</th>"
					+ "<th>Card No</th>"
					+ "<th>CVV</th>"
					+ "<th>Card Type</th>"
					+ "<th>Expire Date</th>"
					+ "<th>Update</th>"
					+ "<th>Remove</th></thead>";
	 
			
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				String payId = Integer.toString(rs.getInt("payId")); 
				String cusId = rs.getString("cusId");
				String telNo = rs.getString("telNo");
				String date = rs.getString("date");
				String amount = rs.getString("amount");
				String cardNo = rs.getString("cardNo");
				String cvv = rs.getString("cvv");
				String cardType = rs.getString("cardType");
				String expDate = rs.getString("expDate");

				// Add into the HTML table 
				output += "<tr><td><input id='hidPaymentIDUpdate' "
						+ "name='hidPaymentIDUpdate' "
						+ "type='hidden' value='" + payId + "'>" 
						+ cusId + "</td>"; 
				output += "<td>" + telNo + "</td>";
				output += "<td>" + date + "</td>";
				output += "<td>" + amount + "</td>";
				output += "<td>" + cardNo + "</td>";
				output += "<td>" + cvv + "</td>";
				output += "<td>" + cardType + "</td>";
				output += "<td>" + expDate + "</td>";

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-payid='" + payId + "'></td>"       
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-payid='" + payId + "'>" + "</td></tr>"; 
		
			}
			con.close(); 
	 
			// Complete the HTML table    
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the Payment.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
	//Add details about the payment
	public String insertPayment(String cusId, String telNo, String date, String amount, String cardNo, String cvv, String cardType , String expDate)  
	{   
		String output = ""; 
	 
		try
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for inserting.";
			} 
	 
			// create a prepared statement 
			String query = " insert into payment (payId , cusId , telNo , date , amount, cardNo, cvv , cardType , expDate)"+ " values (?, ?, ?, ?, ?, ?, ? , ? , ? )"; 
	 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, cusId);
			 preparedStmt.setString(3, telNo);
			 preparedStmt.setString(4, date);
			 preparedStmt.setString(5, amount);
			 preparedStmt.setString(6, cardNo);
			 preparedStmt.setString(7, cvv);
			 preparedStmt.setString(8, cardType);
			 preparedStmt.setString(9, expDate);
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newPayment = readPayment(); 
			output =  "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the Payment.\"}";  
			System.err.println(e.getMessage());   
		} 
		
	  return output;  
	}
	
	//update
	
	public String updatePayment(String payId, String cusId, String telNo, String date, String amount, String cardNo, String cvv, String cardType , String expDate)    
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for updating.";
			} 
	 
			// create a prepared statement    
			String query = "UPDATE payment SET cusId=?,telNo=?,date=?,amount=?,cardNo=?,cvv=?, cardType=?,expDate=? WHERE payId=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, cusId);
			preparedStmt.setString(2, telNo);
			preparedStmt.setString(3, date);
			preparedStmt.setString(4, amount);
			preparedStmt.setString(5, cardNo);
			preparedStmt.setString(6, cvv);
			 preparedStmt.setString(7, cardType);
			 preparedStmt.setString(8, expDate);
			preparedStmt.setInt(9, Integer.parseInt(payId)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newPayment = readPayment();    
			output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the Payment.\"}";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	} 
	
	//delete
	public String deletePayment(String payId)   
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for deleting."; 
			} 
	 
			// create a prepared statement    
			String query = "delete from payment where payId=?";  
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(payId)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newPayment = readPayment();  
			    
			output = "{\"status\":\"success\", \"data\": \"" +  newPayment + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the payment.\"}";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
}
