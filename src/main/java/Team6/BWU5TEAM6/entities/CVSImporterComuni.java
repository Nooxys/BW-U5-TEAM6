package Team6.BWU5TEAM6.entities;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

public class CVSImporterComuni {
    public static void main(String[] args) {
        String csvFile = "./src/assets/comuni-italiani.csv";
        String line = "";
        String csvSplitBy = ";";
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("env.properties"));
            System.out.println("Loading properties successful.");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        String pgUrl = props.getProperty("PG_URL");
        if (pgUrl == null || pgUrl.isEmpty()) {
            System.err.println("The database connection URL was not found or is empty.");
            return;
        }
        System.out.println("Connection URL: " + pgUrl);
        String pgUsername = props.getProperty("PG_USERNAME");
        String pgPassword = props.getProperty("PG_PASSWORD");
        try (Connection connection = DriverManager.getConnection(pgUrl, pgUsername, pgPassword)) {
            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                String headerLine = br.readLine();
                while ((line = br.readLine()) != null) {
                    System.out.println("Read line: " + line);
                    String[] data = line.split(csvSplitBy);
                    String id_district = data[0];
                    String id_municipality = data[1];
                    String name_municipality = data[2];
                    String name_district = data[3];
                    String sql = "INSERT INTO municipalities (id_district, id_municipality, name_municipality, name_district) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement statement = connection.prepareStatement(sql)) {
                        statement.setString(1, id_district);
                        statement.setString(2, id_municipality);
                        statement.setString(3, name_municipality);
                        statement.setString(4, name_district);
                        statement.executeUpdate();
                    }
                    System.out.println("Insertion successful!");
                    System.out.println("Id_district: " + id_district);
                    System.out.println("Id_municipality: " + id_municipality);
                    System.out.println("Municipality: " + name_municipality);
                    System.out.println("District: " + name_district);
                    System.out.println();
                }
                System.out.println("Import completed successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void importCSV() {
    }
}


