package src.View;

import src.Controller.*;
import src.Model.Shelter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ShelterAssignView extends JFrame {

    private JTable table;
    private ShelterController shelterController;
    private AssignmentController assignmentController;

    public ShelterAssignView(
            ShelterController shelterController,
            AssignmentController assignmentController) {

        this.shelterController = shelterController;
        this.assignmentController = assignmentController;

        setTitle("Shelter Assignment");
        setSize(800, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        table = new JTable();
        loadShelterTable();

        JButton btnAssign = new JButton("Start Assignment");
        btnAssign.addActionListener(e -> {
            assignmentController.assign();
            loadShelterTable();
        });

        JButton btnReport = new JButton("Assignment Completed");
        btnReport.addActionListener(e -> {
            new ReportView(assignmentController);
            dispose();
        });

        JPanel bottom = new JPanel();
        bottom.add(btnAssign);
        bottom.add(btnReport);

        add(new JScrollPane(table));
        add(bottom, "South");

        setVisible(true);
    }

    private void loadShelterTable() {

        String[] col = {"Shelter ID", "Capacity", "Current", "Risk Level"};
        DefaultTableModel model = new DefaultTableModel(col, 0);

        for (Shelter s : shelterController.getShelters()) {
            model.addRow(new Object[]{
                    s.getShelterId(),
                    s.getCapacity(),
                    s.getCurrent(),
                    s.getRiskLevel()
            });
        }
        table.setModel(model);
    }
}