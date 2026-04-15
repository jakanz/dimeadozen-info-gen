import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class utilities and methods for use in GenerateInfoFile.
 * 
 * @author Amira Freeman
 */
public class ShowInfo {
    private String artist, tour, date, venue, city;
    private ArrayList<String> tracklist;
    private boolean hasIntroTrack;

    public ShowInfo() {
        // Default values for the sake of avoiding errors
        this.artist = "Placeholder";
        this.tour = "Placeholder";
        this.date = "Placeholder";
        this.venue = "Placeholder";
        this.city = "Placeholder";
        this.tracklist = null;
        this.hasIntroTrack = false;
    }

    private static void promptData(String type) {
        String extra = "";
        if (type.equals("tour")) { extra = " ['n' if not available]"; }
        if (type.equals("date")) { extra = " [yyyy-mm-dd]"; }
        if (type.equals("city")) { extra = " [city, [subnational], country]"; } 
        System.out.print("Enter the " + type + extra + ": ");
    }

    public void setHeaderData() {
        // Not quite pretty but it works
        try (Scanner scan = new Scanner(System.in)) {
            promptData("artist");
            this.artist = scan.nextLine();
            
            promptData("tour");
            if (!scan.nextLine().equals("n")) { this.tour = scan.nextLine(); }
            
            promptData("date");
            this.date = scan.nextLine();
            
            promptData("venue");
            this.venue = scan.nextLine();
            
            promptData("city");
            this.city = scan.nextLine();
        }
    }

    private void determineIntroTrack() {
        try (Scanner scan = new Scanner(System.in)) {
            boolean introTrackConfirmed = false;

            while (!introTrackConfirmed) {
                System.out.print("Do you have an introductory music file ('Intro', 'Banter', etc.)? [y/n] ");
                switch (scan.nextLine().trim().toLowerCase()) {
                    case "y" -> {
                        introTrackConfirmed = true;
                        this.hasIntroTrack = true;
                    }
                    case "n" -> introTrackConfirmed = true;
                    default -> System.out.println("What you entered could not be parsed by the program. Please try again.");
                }
            }
            if (this.hasIntroTrack) { this.tracklist.add("Intro"); }
        }
    }

    public void setTracklistData() {
        determineIntroTrack();
        try (Scanner scan = new Scanner(System.in)) {
            String otherThanIntro = "";
            if (hasIntroTrack) { otherThanIntro = " [other than the intro track]"; }
            System.out.print("Enter the number of songs in your tracklist" + otherThanIntro + ": ");
            int tracklistLength = Integer.parseInt(scan.nextLine());

            for (int i = 0; i < tracklistLength; i++) {
                System.out.print("Enter the title of the next track: ");
                this.tracklist.add(scan.nextLine().trim());
            }

            System.out.print("What is the total runtime of the tracklist? ");
        } 
    }

    public void setSources() {

    }

    // Get methods
    public String getArtist() { return this.artist; }
    public String getTour() { return this.tour; }
    public String getDate() { return this.date; }
    public String getVenue() { return this.venue; }
    public String getCity() { return this.city; }
    public ArrayList<String> getTracklist() { return this.tracklist; }
}
