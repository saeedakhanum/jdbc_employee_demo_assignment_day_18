package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeJdbc {

	private static final String URL = "jdbc:mysql://localhost:3306/demo_db";
	private static final String USER = "root";
	private static final String PASSWORD = "test";

	public static void main(String[] args) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);) {
			System.out.println("Connected to the database!");
			//insertEmployee(conn,"Bob","bob@gmail.com");
			//updateEmployee(conn,"Eva","eva@gmail.com",2);
			//selectEmployee(conn);
			deleteEmployee(conn,2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	public static void insertEmployee(Connection conn, String empName, String empEmail) {
		String sql = "insert into employee(emp_name,emp_email) values('" + empName + "','" + empEmail + "')";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			int rows = ps.executeUpdate();
			System.out.println("Inserted record : " + rows);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	
	public static void insertEmployee(Connection conn,String empName,String empEmail) {
		String sql="insert into employee(emp_name,emp_email) values(?,?)";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1, empName);
			ps.setString(2, empEmail);
			int rows = ps.executeUpdate();
			System.out.println("Inserted records : "+rows);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateEmployee(Connection conn,String empName,String empEmail,int empId) {
		String sql="update employee set emp_name=?,emp_email=? where emp_id=?";
		try(PreparedStatement ps= conn.prepareStatement(sql)){
			ps.setString(1, empName);
			ps.setString(2, empEmail);
			ps.setInt(3,empId);
			int rows=ps.executeUpdate();
			System.out.println("Updated records : "+rows);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void selectEmployee(Connection conn) {
		String sql= "select * from employee";
		try(PreparedStatement ps= conn.prepareStatement(sql)){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int empId=rs.getInt(1);
				String empName=rs.getString(2);
				String empEmail=rs.getString(3);
				System.out.println(empId+" : "+empName+" : "+empEmail );
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteEmployee(Connection conn,int empId) {
		String sql="delete from employee where emp_id=?";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setInt(1, empId);
			int rows=ps.executeUpdate();
			System.out.println("Deleted records : "+rows);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
