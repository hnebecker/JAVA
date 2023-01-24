/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;
/**
 *
 * @author hnebe
 */
public class ProjectController {
	public void save(Project project) throws Exception{
		String sql = "INSERT INTO `projects` ("
			+ " `name`,"
			+ " `description`,"
			+ " `createdAt`,"
			+ " `updatedAt`) "
			+ "VALUES (?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement statement = null;
		
		
		try {  //conectando com o bd
			conn = ConnectionFactory.getConnection();
			statement = conn.prepareStatement(sql);
			//setando o ? da string
			statement.setString(1, project.getName());
			statement.setString(2, project.getDescription());
			statement.setDate(3, new java.sql.Date(project.getCreatedAt().getTime()));
			statement.setDate(4, new java.sql.Date(project.getUpdatedAt().getTime()));
		
			//executando a ação
			statement.execute();
				
			} catch (SQLException e) {
				throw new Exception("Erro ao salvar no Banco de Dados." + e.getMessage()); 
			} finally {
				   if (conn != null){
					   ConnectionFactory.closeConnection(conn);
				   }
					   
			}
		
	};
	public void update(Project project) throws Exception{
		String sql = "UPDATE `projects` SET "
		+ " `name` = ?,"
		+ " `description` = ?,"
		+ " `createdAt` = ?,"
		+ " `updatedAt` =? "
		+ "WHERE `id` = ?";	
		
		Connection conn = null;
		PreparedStatement statement = null;
		
		
		try {  //conectando com o bd
			conn = ConnectionFactory.getConnection();
			statement = conn.prepareStatement(sql);
			//setando o ? da string
			
			
			statement.setString(1, project.getName());
			statement.setString(2, project.getDescription());
			statement.setDate(3, new java.sql.Date(project.getCreatedAt().getTime()));
			statement.setDate(4, new java.sql.Date(project.getUpdatedAt().getTime()));
			statement.setInt(5, project.getId());
		
			//executando a ação
			statement.execute();
				
			} catch (Exception ex) {
				throw new Exception("Erro ao atualizar o projeto"
					+ " no Banco de Dados." + ex.getMessage()); 
			} finally {
				   if (conn != null){
					   ConnectionFactory.closeConnection(conn);
				   }
					   
			}
		
		
		
	}
	public void removeById(int idProject) throws Exception{
		String sql = "DELETE FROM `projects` WHERE ?";
		
		Connection conn = null;
		PreparedStatement statement = null;
		
		
		try {  //conectando com o bd
			conn = ConnectionFactory.getConnection();
			statement = conn.prepareStatement(sql);
			//setando o ? da string
			statement.setInt(1, idProject);
			//executando a ação
			statement.execute();
				
			} catch (SQLException e) {
				throw new Exception("Erro ao deletar do Banco de Dados." + e.getMessage()); 
			} finally {
				   if (conn != null){
					   ConnectionFactory.closeConnection(conn);
				   }
					   
			}
		
	};
	public List<Project> getAll(){
		
		String sql = "SELECT * FROM `projects`";
		
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Project> projects = new ArrayList<Project>();
		
		try {  //conectando com o bd
			conn = ConnectionFactory.getConnection();
			statement = conn.prepareStatement(sql);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				
				Project project = new Project();
				
				project.setId(resultSet.getInt("id"));
				project.setName(resultSet.getString("name"));
				project.setDescription(resultSet.getString("description"));
				project.setCreatedAt(resultSet.getDate("createdAt"));
				project.setUpdatedAt(resultSet.getDate("updatedAt"));
				
				projects.add(project);}
			
			
				
			} catch (SQLException e) {
				throw new RuntimeException("Erro ao carregar do Banco de Dados." + e.getMessage(), e); 
			} finally {
				   ConnectionFactory.closeConnection(conn, statement, resultSet);
					   
			}
		//lista de projetos que foi criada e carregada do bd.
		return projects;
	}


}



	
	

