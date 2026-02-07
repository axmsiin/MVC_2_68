package src.View;

import src.Controller.*;
import src.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CitizenRegisterView extends JFrame {

    private JTable table;
    private CitizenController controller = new CitizenController();

    public CitizenRegisterView() {
        setTitle("Citizen Registration");
        setSize(900, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JComboBox<String> combo =
                new JComboBox<>(new String[]{"ALL", "GENERAL", "RISK", "VIP"});

        table = new JTable();
        loadTable("ALL");

        combo.addActionListener(e ->
                loadTable(combo.getSelectedItem().toString()));

        JButton btnNext = new JButton("Go to Shelter Assignment");
        btnNext.addActionListener(e -> {

            DatabaseCitizen citizenDB = new DatabaseCitizen();
            DatabaseShelter shelterDB = new DatabaseShelter();
            DatabaseAssignment assignDB = new DatabaseAssignment();

            ShelterController shelterController =
                    new ShelterController(shelterDB);

            AssignmentController assignmentController =
                    new AssignmentController(citizenDB, shelterDB, assignDB);

            new ShelterAssignView(shelterController, assignmentController);
            dispose();
        });

        JPanel top = new JPanel();
        top.add(new JLabel("Type:"));
        top.add(combo);

        add(top, "North");
        add(new JScrollPane(table));
        add(btnNext, "South");

        setVisible(true);
    }

    private void loadTable(String type) {

        String[] col = {
                "CitizenID",
                "Age",
                "Health Status",
                "Register Date",
                "Type"
        };

        DefaultTableModel model = new DefaultTableModel(col, 0);
        List<Citizen> list = controller.getByType(type);

        for (Citizen c : list) {
            model.addRow(new Object[]{
                    c.getCitizenId(),
                    c.getAge(),
                    c.getHealthStatus(),
                    c.getRegisterDate(),
                    c.getCitizenType()
            });
        }
        table.setModel(model);
    }
}