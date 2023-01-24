/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author hnebe
 */
public class ConnectionFactory {
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";	
	public static final String URL = "jdbc:mysql://localhost:3306/todoapp";
	public static final String USER = "root";	
	public static final String PASS = "";
	
	public static Connection getConnection(){
	try {
		Class.forName(DRIVER);
		return DriverManager.getConnection(URL, USER, PASS);
	}	catch (Exception e){
		throw new RuntimeException ("Erro ao conectar ao Banco de dados", e);
	}
	}
	
	public static void closeConnection(Connection conn){
		try{
			if(conn!=null){
				conn.close();
			}
		}catch (Exception e){
			throw new RuntimeException ("Erro ao fechar a conexão com Banco de dados.", e);
		}
	}
	
	
		
	public static void closeConnection(Connection conn, PreparedStatement statement){
		
		try{
			if(conn!=null){
				conn.close();
			}
			if(statement != null){
				statement.close();
			}
		}catch (Exception e){
			throw new RuntimeException ("Erro ao fechar a conexão com Banco de dados.", e);
		}
	
	
	
		}
	
	public static void closeConnection(Connection conn, PreparedStatement statement, ResultSet resultSet){
		try{
			if(conn!=null){
				conn.close();
			}
			if(statement != null){
				statement.close();
			}
			if(resultSet!= null){
				resultSet.close();
			}
		}catch (Exception e){
			throw new RuntimeException ("Erro ao fechar a conexão com Banco de dados.", e);
		}
	}
	
	
		}
	
