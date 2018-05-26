package com.storage.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDao {
	private static String jdbcdriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static  String url="jdbc:sqlserver://localhost:8000;DatabaseName=Storage";
	private static String username="sa";
	private static String pwd="123456"; 
	
	public static Connection GetConnect(){
		Connection conn=null;
		try{
		Class.forName(jdbcdriver);
		conn=DriverManager.getConnection(url,username,pwd);
		}catch(ClassNotFoundException e){
			
		}catch(SQLException  e){
			
		}		
	   return conn;		
	}
	
	public static void CloseConnect(Connection conn){
		try{
		conn.close();
		}catch (SQLException e){
			System.out.print(e.getMessage());
		}
	
	}

}
