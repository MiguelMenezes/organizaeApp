/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;


import controller.ProjectController;
import controller.TaskController;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Project;
import model.Task;
import util.ConnectionFactory;

/**
 *
 * @author migtr
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        ProjectController projectController = new ProjectController();
        TaskController taskController = new TaskController();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        Project p = new Project();
        p.setName("Projeto Teste 3");
        p.setDescription("Testando a criação do projeto 3");
        p.setCreatedAt(Calendar.getInstance().getTime());
        p.setUpdatedat(Calendar.getInstance().getTime());
        projectController.addProject(p);
        
        Task task = new Task();
        task.setIdProject(p.getId());
        task.setName("Nome da tarefa");
        task.setDescription("Descrição da tarefa aqui.");
        Date deadLine = null;
        try {
            deadLine = dateFormat.parse("25/12/2022");
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        task.setDeadline(deadLine);
        task.setIsCompleted(false);
        task.setCreatedAt(Calendar.getInstance().getTime());
        task.setUpdatedAt(Calendar.getInstance().getTime());
        task.setNotes("Notas aqui.");
        taskController.save(task);
        
        
    }
    
}
