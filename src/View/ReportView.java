package src.View;

import src.Controller.AssignmentController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ReportView extends JFrame {

    private AssignmentController controller;
    private JTable table;

    // ✅ constructor ที่รับ controller
    public ReportView(AssignmentController controller) {

        this.controller = controller;

        setTitle("Assignment Report");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        table = new JTable();
        loadReport();

        add(new JScrollPane(table));
        setVisible(true);
    }

    private void loadReport() {

        String[] col = {
                "Citizen ID",
                "Citizen Type",
                "Shelter ID",
                "Status"
        };

        DefaultTableModel model = new DefaultTableModel(col, 0);

        List<String[]> report = controller.getReport();

        for (String[] row : report) {
            model.addRow(row);
        }

        table.setModel(model);
    }
}
