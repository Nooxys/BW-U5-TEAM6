package Team6.BWU5TEAM6.entities;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

public class CSVImporterProvince {

    public static void main(String[] args) {
        String csvFile = "./src/assets/province-italiane.csv";
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
            System.err.println("Database connection URL not found or empty.");
            return;
        }
        System.out.println("Connection URL: " + pgUrl);

        String pgUsername = props.getProperty("PG_USERNAME");
        String pgPassword = props.getProperty("PG_PASSWORD");

        try (Connection connection = DriverManager.getConnection(pgUrl, pgUsername, pgPassword)) {
            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                String headerLine = br.readLine();
                while ((line = br.readLine()) != null) {
                    System.out.println("Line read: " + line);
                    String[] data = line.split(csvSplitBy);
                    String sigla = data[0];
                    String district = data[1];
                    String region = data[2];
                    String sql = "INSERT INTO districts (sigla, district, region) VALUES (?, ?, ?)";
                    try (PreparedStatement statement = connection.prepareStatement(sql)) {
                        statement.setString(1, sigla);
                        statement.setString(2, district);
                        statement.setString(3, region);
                        statement.executeUpdate();
                    }
                    System.out.println("Insertion successful!");
                    System.out.println("Sigla: " + sigla);
                    System.out.println("Provincia: " + district);
                    System.out.println("Regione: " + region);
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
