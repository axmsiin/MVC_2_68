package src.Model;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DatabaseCitizen {

    private List<Citizen> citizens = new ArrayList<>();

    public DatabaseCitizen() {
        loadCSV("data/citizens.csv");
    }

    private void loadCSV(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            br.readLine(); // skip header
            String line;

            while ((line = br.readLine()) != null) {
                String[] r = line.split(",");

                // คนหนึ่งลงทะเบียนได้ครั้งเดียว
                boolean exists = citizens.stream()
                        .anyMatch(c -> c.getCitizenId().equals(r[0]));
                if (exists) continue;

                citizens.add(new Citizen(
                        r[0],
                        Integer.parseInt(r[1]),
                        r[2],
                        LocalDate.parse(r[3]),
                        r[4]
                ));
            }
        } catch (Exception e) {
            System.out.println("อ่าน citizens.csv ไม่ได้");
        }
    }

    public List<Citizen> getAll() {
        return citizens;
    }
}
