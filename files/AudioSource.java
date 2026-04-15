import java.util.Scanner;

public class AudioSource {
    private String source, quality, location, videographer;

    public AudioSource() {}

    private static String promptData(String type, Scanner scan) {
        System.out.print("Enter the " + type + ": ");
        return scan.nextLine().trim();
    }

    public void setSourceData(Scanner scan) {
        this.source = promptData("source [SBD/AUD]", scan);
        this.quality = promptData("quality [1-10]", scan);                    
        this.location = promptData("location of recording", scan);
        this.videographer = promptData("taper's name", scan);
    }
}
