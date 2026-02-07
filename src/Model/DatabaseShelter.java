package src.Model;

import java.io.*;
import java.util.*;

public class DatabaseShelter {

    private List<Shelter> shelters = new ArrayList<>();

    public DatabaseShelter() {
        loadCSV("data/shelters.csv");
    }

    private void loadCSV(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            br.readLine();
            String line;

            while ((line = br.readLine()) != null) {
                String[] r = line.split(",");

                shelters.add(new Shelter(
                        r[0],
                        Integer.parseInt(r[1]),
                        r[2]
                ));
            }
        } catch (Exception e) {
            System.out.println("อ่าน shelters.csv ไม่ได้");
        }
    }

    public List<Shelter> getAll() {
        return shelters;
    }
}
