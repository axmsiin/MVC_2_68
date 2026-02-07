package src.Model;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DatabaseAssignment {

    private List<Assignment> assignments = new ArrayList<>();
    private final String FILE_PATH = "data/Assignments.csv";

    public DatabaseAssignment() {
        loadCSV();
    }

    // ===== โหลด CSV =====
    private void loadCSV() {
        assignments.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {

            String line = br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                String[] r = line.split(",");

                assignments.add(new Assignment(
                        r[0],                      // citizenId
                        r[1],                      // shelterId
                        LocalDate.parse(r[2])      // assignDate
                ));
            }

        } catch (Exception e) {
            System.out.println("Cannot read Assignments.csv");
        }
    }

    // ===== เขียน CSV =====
    private void saveCSV() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {

            pw.println("citizenId,shelterId,assignDate");

            for (Assignment a : assignments) {
                pw.println(
                        a.getCitizenId() + "," +
                        a.getShelterId() + "," +
                        a.getAssignDate()
                );
            }

        } catch (IOException e) {
            System.out.println("Cannot write Assignments.csv");
        }
    }

    // ===== public methods =====
    public List<Assignment> getAll() {
        return assignments;
    }

    public boolean isAssigned(String citizenId) {
        return assignments.stream()
                .anyMatch(a -> a.getCitizenId().equals(citizenId));
    }

    public void add(Assignment a) {
        assignments.add(a);
        saveCSV();   // 🔥 สำคัญมาก
    }

    // ใช้เมื่อต้อง sync ใหม่
    public void reload() {
        loadCSV();
    }
}
