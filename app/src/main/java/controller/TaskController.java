/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Task;
import util.ConnectionFactory;

/**
 *
 * @author hnebe
 */
public class TaskController {
	public void save(Task task) throws Exception{
		String sql = "INSERT INTO `tasks` (`id`,"
			+ " `idProject`,"
			+ " `name`,"
			+ " `description`,"
			+ " `completed`,"
			+ " `notes`,"
			+ " `deadline`,"
			+ " `createdAt`,"
			+ " `updatedAt`) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement statement = null;
		
		
		try {  //conectando com o bd
			conn = ConnectionFactory.getConnection();
			statement = conn.prepareStatement(sql);
			//setando o ? da string
			statement.setInt(1, task.getId());
			statement.setInt(2, task.getIdProject());
			statement.setString(3, task.getName());
			statement.setString(4, task.getDescription());
			statement.setBoolean(5, task.isIsCompleted());
			statement.setString(6, task.getNotes());
			statement.setDate(7, new java.sql.Date(task.getDeadline().getTime()));
			statement.setDate(8, new java.sql.Date(task.getCreatedAt().getTime()));
			statement.setDate(9, new java.sql.Date(task.getUpdatedAt().getTime()));
		
			//executando a ação
			statement.execute();
				
			} catch (SQLException e) {
				throw new Exception("Erro ao salvar no Banco de Dados." + e.getMessage()); 
			} finally {
					   ConnectionFactory.closeConnection(conn,statement);
				   }
					   
		
	};
	public void update(Task task) throws Exception{
		String sql = "UPDATE `tasks` SET " 
		+ " `idProject` = ?,"
		+ " `name` = ?,"
		+ " `description` = ?,"
		+ " `completed` = ?,"
		+ " `notes` = ?,"
		+ " `deadline` = ?,"
		+ " `createdAt` = ?,"
		+ " `updatedAt` = ? "
		+ "WHERE `id` = ?";	
		
		Connection conn = null;
		PreparedStatement statement = null;
		
		
		try {  //conectando com o bd
			conn = ConnectionFactory.getConnection();
			statement = conn.prepareStatement(sql);
			//setando o ? da string
			
			statement.setInt(1, task.getIdProject());
			statement.setString(2, task.getName());
			statement.setString(3, task.getDescription());
			statement.setBoolean(4, task.isIsCompleted());
			statement.setString(5, task.getNotes());
			statement.setDate(6, new java.sql.Date(task.getDeadline().getTime()));
			statement.setDate(7, new java.sql.Date(task.getCreatedAt().getTime()));
			statement.setDate(8, new java.sql.Date(task.getUpdatedAt().getTime()));
			statement.setInt(9, task.getId());
		
			//executando a ação
			statement.execute();
				
			} catch (SQLException e) {
				throw new Exception("Erro ao atualizar a tarefa no Banco de Dados." + e.getMessage()); 
			} finally {
					   ConnectionFactory.closeConnection(conn, statement);
					   
			}
		
		
	}
	public void removeById(int taskId){
		String sql = "DELETE FROM `tasks` WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement statement = null;
		
		
		try {  //conectando com o bd
			conn = ConnectionFactory.getConnection();
			statement = conn.prepareStatement(sql);
			//setando o ? da string
			statement.setInt(1, taskId);
			//executando a ação
			statement.execute();
				
			} catch (SQLException e) {
			try { 
				throw new Exception("Erro ao deletar do Banco de Dados." + e.getMessage());
			} catch (Exception ex) {
				Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
			}
			} finally {
				   ConnectionFactory.closeConnection(conn, statement);
					   
			}
		
	};
	public List<Task> getAll(int idProject){
		
		String sql = "SELECT * FROM `tasks` WHERE `idProject` = ?";
		
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Task> tasks = new ArrayList<>();
		
		try {  //conectando com o bd
			conn = ConnectionFactory.getConnection();
			statement = conn.prepareStatement(sql);
			
			//setando o ? da string
			statement.setInt(1, idProject);
			//executando a ação
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				
				Task task = new Task();
				task.setId(resultSet.getInt("id"));
				task.setIdProject(resultSet.getInt("idProject"));
				task.setName(resultSet.getString("name"));
				task.setDescription(resultSet.getString("description"));
				task.setNotes(resultSet.getString("notes"));
				task.setIsCompleted(resultSet.getBoolean("completed"));
				task.setDeadline(resultSet.getDate("deadline"));
				task.setCreatedAt(resultSet.getDate("createdAt"));
				task.setUpdatedAt(resultSet.getDate("updatedAt"));
				
				tasks.add(task);}
			
			
				
			} catch (SQLException e) {
				throw new RuntimeException("Erro ao deletar do Banco de Dados." + e.getMessage(), e); 
			} finally {
				   ConnectionFactory.closeConnection(conn, statement, resultSet);
					   
			}
		//lista de tarefas que foi criada e carregada do bd.
		return tasks;
	}
	
}
