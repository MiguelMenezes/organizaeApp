/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

/**
 *
 * @author migtr
 */
public class ProjectController {

    public void addProject(Project project) {

        String comandoSql = "INSERT INTO projects(name, description, createdAt, updatedAt) VALUES (?, ?, ?, ?)";
                

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(comandoSql);

            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedat().getTime()));
            statement.execute();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar novo projeto" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }

    }

    public void removeProjectById(int id) {
        String comandoSql = "DELETE FROM projects WHERE id = ?";
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(comandoSql);

            statement.setInt(1, id);
            statement.execute();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir o projeto" + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }
    }

    public void updateProject(Project project) {
        String comandoSql = "UPDATE projects SET name = ?, description = ?, "
                + "createdAt = ?, updatedAt = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(comandoSql);

            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedat().getTime()));
            statement.setInt(5, project.getId());
            statement.execute();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o projeto" + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }

    }

    public List<Project> getAllProjects() {
        String comandoSql = "SELECT * FROM projects";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        List<Project> projects = new ArrayList<>();

        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(comandoSql);
            result = statement.executeQuery();

            while (result.next()) {
                Project project = new Project();
                project.setId(result.getInt("id"));
                project.setName(result.getString("name"));
                project.setDescription(result.getString("description"));
                project.setCreatedAt(result.getDate("createdAt"));
                project.setUpdatedat(result.getDate("updatedAt"));

                projects.add(project);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar os projetos" + e.getMessage(), e);
        }finally{
            ConnectionFactory.closeConnection(conn, statement, result);
        }

        return projects;
    }

}
