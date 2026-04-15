import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class utilities and objects for use in GenerateInfoFile.
 * 
 * @author Amira Freeman
 */
public class ShowInfo {
    private static String artist, tour, date, venue, city;
    private static ArrayList<String> tracklist;
    private static boolean hasIntroTrack;

    public ShowInfo(String artist, String tour, String date, String venue, String city) {
        ShowInfo.artist = artist;
        ShowInfo.tour = tour;
        ShowInfo.date = date;
        ShowInfo.venue = venue;
        ShowInfo.city = city;
    }

    /**
     * Prints the basic message prompting for a type of data, to be followed
     * by a variable assignment using the Scanner object from main.
     * 
     * @param type Type of data to prompt for (e.g. artist, date, city, etc.)
     */
    private static void promptData(String type) {
        String extra = "";
        if (type.equals("tour")) { extra = " ['n' if not available]"; }
        if (type.equals("date")) { extra = " [yyyy-mm-dd]"; }
        if (type.equals("city")) { extra = " [city, [subnational], country]"; } 
        System.out.print("Enter the " + type + extra + ": ");
    }

    /**
     * Uses the promptData() method to print a message, then assigns the object's
     * variables via scanning the next line
     */
    public static void setHeaderData() {
        // Not quite pretty but it works
        try (Scanner scan = new Scanner(System.in)) {
            promptData("artist");
            ShowInfo.artist = scan.nextLine();
            
            promptData("tour");
            if (!scan.nextLine().equals("n")) { ShowInfo.tour = scan.nextLine(); }
            
            promptData("date");
            ShowInfo.date = scan.nextLine(); // Ideally in the form of yyyy-mm-dd, but this format changes across some users and is intentionally left unformatted
            
            promptData("venue");
            ShowInfo.venue = scan.nextLine();
            
            promptData("city");
            ShowInfo.city = scan.nextLine(); // Ideally in the form of "City, [ST,] Country", but this format and data changes across some users
        }
    }

    private static void determineIntroTrack() {
        try (Scanner scan = new Scanner(System.in)) {
            boolean introTrackConfirmed = false;

            while (!introTrackConfirmed) {
                System.out.print("Do you have an introductory music file ('Intro', 'Banter', etc.) [y/n]? ");
                switch (scan.nextLine().trim().toLowerCase()) {
                    case "y" -> {
                        introTrackConfirmed = true;
                        ShowInfo.hasIntroTrack = true;
                    }
                    case "n" -> introTrackConfirmed = true;
                    default -> System.out.println("What you entered could not be parsed by the program. Please try again.");
                }
            }
            if (ShowInfo.hasIntroTrack) { ShowInfo.tracklist.add("Intro"); }
        }
    }

    public static void setTracklistData() {
        determineIntroTrack();
        try (Scanner scan = new Scanner(System.in)) {
            int tracklistLength = 0;
            String otherThanIntro = "";

            if (hasIntroTrack) { otherThanIntro = " [other than the intro track]"; }
            System.out.print("Enter the number of songs in your tracklist" + otherThanIntro + ": ");
            tracklistLength = Integer.parseInt(scan.nextLine());

            for (int i = 0; i < tracklistLength; i++) {
                ShowInfo.tracklist.add(scan.nextLine().trim());
            }
        } 
    }

    // Get methods
    public String getArtist() { return ShowInfo.artist; }
    public String getTour() { return ShowInfo.tour; }
    public String getDate() { return ShowInfo.date; }
    public String getVenue() { return ShowInfo.venue; }
    public String getCity() { return ShowInfo.city; }
    public ArrayList<String> getTracklist() { return ShowInfo.tracklist; }
}
