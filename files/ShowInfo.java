import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class utilities and methods for use in GenerateInfoFile.
 * 
 * @author Amira Freeman
 */
public class ShowInfo {
    private String contrastClause, artist, tour, date, venue, city, runtime, lineage, otherFilesSummary, notes;
    private ArrayList<String> tracklist = new ArrayList<>();
    private ArrayList<AudioSource> audioSources = new ArrayList<>();
    private ArrayList<VideoSource> videoSources = new ArrayList<>();
    private boolean hasIntroTrack;

    public ShowInfo() {}

    private static String promptData(String type, Scanner scan) {
        System.out.print("Enter the " + type + ": ");
        String data = scan.nextLine().trim();

        if (data.equals("n") && type.equals("tour ['n' if not available]")) { return ""; }
        return data;
    }

    private void determineIntroTrack(Scanner scan) {
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

    public void setContrastClause(Scanner scan) {
        boolean requiresContrast = false, contrastConfirmed = false;
        while (!contrastConfirmed) {
            System.out.print("Does this torrent require a contrast clause? [y/n] ");
            switch (scan.nextLine().trim().toLowerCase()) {
                case "y" -> {
                    contrastConfirmed = true;
                    requiresContrast = true;
                }
                case "n" -> contrastConfirmed = true;
                default -> System.out.println("What you entered could not be parsed by the program. Please try again.");
            }
        }
        if (requiresContrast) { this.contrastClause = promptData("full contrast clause", scan); }
    }

    public void setHeaderData(Scanner scan) {
        this.artist = promptData("name of the artist", scan);
        this.tour = promptData("name of the tour ['n' if not applicable]", scan);
        this.date = promptData("date of the event [yyyy-mm-dd]", scan);
        this.venue = promptData("venue which the files were captured", scan);
        this.city = promptData("city of the event [city, [subnational], country]", scan);
        System.out.println();
    }

    public void setTracklistData(Scanner scan) {
        determineIntroTrack(scan);

        String otherThanIntro = "";
        if (hasIntroTrack) { otherThanIntro = " [other than the intro track]"; }
        System.out.print("Enter the number of songs in your tracklist" + otherThanIntro + ": ");
        int tracklistLength = Integer.parseInt(scan.nextLine());

        for (int i = 0; i < tracklistLength; i++) {
            System.out.print("Enter the title of the next track: ");
            this.tracklist.add(scan.nextLine().trim());
        }

        System.out.print("What is the total runtime of the tracklist? ");
        this.runtime = scan.nextLine().trim();
        System.out.println();
    }

    public void setSources(Scanner scan) {
        boolean sourceComplete = false;
        System.out.print("How many sources does your torrent originate from? ");
        int numSources = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < numSources; i++) {
            sourceComplete = false;
            while (!sourceComplete) {
                System.out.print("Is your source an audio or video source? [a/v] ");
                switch (scan.nextLine().trim()) {
                    case "v" -> {
                        VideoSource video = new VideoSource();
                        video.setSourceData(scan);
                        this.videoSources.add(video);
                        sourceComplete = true;
                    }
                    case "a" -> {
                        AudioSource audio = new AudioSource();
                        audio.setSourceData(scan);
                        this.audioSources.add(audio);
                        sourceComplete = true;
                    }
                    default -> System.out.println("What you entered could not be parsed by the program. Please try again.");
                }
            }
            String nextSectionTransition = "\n";
            if (i != numSources - 1) { nextSectionTransition = "Moving to next source...\n"; }
            System.out.println(nextSectionTransition);
        }
    }
    
    public void setLineage(Scanner scan) {
        boolean confirm = false, hasLineageBlock = false;
        while (!confirm) {
            System.out.print("Does this torrent have known lineage? [y/n] ");
            switch (scan.nextLine().trim().toLowerCase()) {
                case "y" -> {
                    confirm = true;
                    hasLineageBlock = true;
                }
                case "n" -> confirm = true;
                default -> System.out.println("What you entered could not be parsed by the program. Please try again.");
            }
        }
        if (hasLineageBlock) { this.contrastClause = promptData("full lineage [A > B > C > ...]", scan); }
    }

    public void setOtherFiles(Scanner scan) {
        boolean confirm = false, hasOtherFilesBlock = false;
        while (!confirm) {
            System.out.print("Does this torrent have other files than music, info, and hash files? [y/n] ");
            switch (scan.nextLine().trim().toLowerCase()) {
                case "y" -> {
                    confirm = true;
                    hasOtherFilesBlock = true;
                }
                case "n" -> confirm = true;
                default -> System.out.println("What you entered could not be parsed by the program. Please try again.");
            }
        }
        if (hasOtherFilesBlock) { this.otherFilesSummary = promptData("summary of other files present", scan); }
    }

    public void setNotes(Scanner scan) {
        boolean confirm = false, hasNotes = false;
        while (!confirm) {
            System.out.print("Does this torrent have notes (whether of the taper or uploader)? [y/n] ");
            switch (scan.nextLine().trim().toLowerCase()) {
                case "y" -> {
                    confirm = true;
                    hasNotes = true;
                }
                case "n" -> confirm = true;
                default -> System.out.println("What you entered could not be parsed by the program. Please try again.");
            }
        }
        if (hasNotes) { this.notes = promptData("full notes", scan); }
    }

    // public String toString() {
    //     // TODO return whole file as string
    // }
}
