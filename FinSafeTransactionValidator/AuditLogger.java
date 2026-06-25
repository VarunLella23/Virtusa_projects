import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class AuditLogger {

    public static void log(String message) {

        try {

            FileWriter writer = new FileWriter("audit_log.txt", true);

            writer.write(LocalDateTime.now() + " - " + message + "\n");

            writer.close();

        } catch (IOException e) {

            System.out.println("Error writing log file.");

        }

    }

}