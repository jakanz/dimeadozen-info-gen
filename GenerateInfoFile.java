import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Generates an info.txt using data prompted from the user
 * to be used in torrents shared on www.DimeADozen.org.
 * 
 * @author Amira Freeman
 */
public class GenerateInfoFile {
    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            boolean confirmedReady = false;
            while (!confirmedReady) {
                System.out.print("This program is strictly for those whose files are ready to be published with the exception of an info.txt file. Are these files already prepared [y/n]? ");
                switch (scan.nextLine().trim().toLowerCase()) {
                    case "y" -> confirmedReady = true;
                    case "n" -> System.exit(1);
                    default -> System.out.println("What you entered could not be parsed by the program. Please try again.");
                }
            }
        }

        ShowInfo show = new ShowInfo();
        show.setHeaderData();
        show.setTracklistData();

        Path path = Paths.get("info.txt");
    }
}