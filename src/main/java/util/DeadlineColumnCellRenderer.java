/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Color;
import java.awt.Component;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import model.Task;

/**
 *
 * @author migtr
 */
public class DeadlineColumnCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value,
                isSelected, hasFocus, row, column);
        label.setHorizontalAlignment(CENTER);
        //pega o model da jtable:
        TaskTableModel taskModel = (TaskTableModel) table.getModel();
        //pega a tarefa do model:
        Task task = taskModel.getTasks().get(row);
        //verifica se o prazo se está vencido:
        if (task.getDeadline().after(new Date())) {

            label.setBackground(Color.GREEN);

        } else {

            label.setBackground(Color.RED);

        }
        return label;
    }

}
