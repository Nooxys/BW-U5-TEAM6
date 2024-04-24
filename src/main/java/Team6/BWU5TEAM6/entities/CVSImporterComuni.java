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
            System.out.println("Caricamento delle proprietà riuscito.");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        String pgUrl = props.getProperty("PG_URL");
        if (pgUrl == null || pgUrl.isEmpty()) {
            System.err.println("L'URL di connessione al database non è stato trovato o è vuoto.");
            return;
        }
        System.out.println("URL di connessione: " + pgUrl);
        String pgUsername = props.getProperty("PG_USERNAME");
        String pgPassword = props.getProperty("PG_PASSWORD");
        try (Connection connection = DriverManager.getConnection(pgUrl, pgUsername, pgPassword)) {
            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                String headerLine = br.readLine();
                while ((line = br.readLine()) != null) {
                    System.out.println("Riga letta: " + line);
                    String[] data = line.split(csvSplitBy);
                    String id_provincia = data[0];
                    String id = data[1];
                    String comune = data[2];
                    String provincia = data[3];
                    String sql = "INSERT INTO comuni (id_provincia, id, comune, provincia) VALUES (?, ?, ?,?)";
                    try (PreparedStatement statement = connection.prepareStatement(sql)) {
                        statement.setString(1, id_provincia);
                        statement.setString(2, id);
                        statement.setString(3, comune);
                        statement.setString(4, provincia);
                        statement.executeUpdate();
                    }
                    System.out.println("Inserimento avvenuto con successo!");
                    System.out.println("Id_provincia: " + id_provincia);
                    System.out.println("Id: " + id);
                    System.out.println("Comune: " + comune);
                    System.out.println("Provincia: " + provincia);
                    System.out.println();
                }
                System.out.println("Importazione completata con successo!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
