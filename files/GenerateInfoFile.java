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
        Scanner scan = new Scanner(System.in);
        ShowInfo show = new ShowInfo();

        flushTerminal();
        printOpener();
        confirmReady(scan);
        setAllValues(show, scan);
        scan.close();

        Path path = Paths.get("info.txt");
        System.out.println(show.toString());
    }

    private static void flushTerminal() {
        // Console flush for opener text (or give appearance of a flush... lol)
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    private static void printOpener() {
        System.out.println("(c) 2026 Amira Freeman via MIT License\n\nThis program is designed with the creator's personal info.txt preferences\nin mind, and will only output as such. Reformatting any information in the\nfinal product is your responsibility. As a final warning, the creator of\nthis program is not solely responsible for any mistakes or accidental\nomissions in your torrent submissions. It is your responsibility to\nensure your torrent does not violate DIME's Terms of Service, especially\nin regards to allowed artists, events, venues, and information requirements.\nhttp://wiki.dimeadozen.org\n");
    }

    private static void confirmReady(Scanner scan) {
        boolean confirmedReady = false;
        while (!confirmedReady) {
            System.out.print("This program is strictly for those whose files are ready to be published with\nthe exception of an info.txt file. Are these files already prepared? [y/n] ");
            switch (scan.nextLine().trim().toLowerCase()) {
                case "y" -> confirmedReady = true;
                case "n" -> System.exit(1);
                default -> System.out.println("What you entered could not be parsed by the program. Please try again.");
            }
        }
        System.out.println();
    }

    private static void setAllValues(ShowInfo show, Scanner scan) {
        show.setContrastClause(scan);
        show.setHeaderData(scan);
        show.setTracklistData(scan);
        show.setSources(scan);
        show.setLineage(scan);
        show.setOtherFiles(scan);
        show.setNotes(scan);
    }
}