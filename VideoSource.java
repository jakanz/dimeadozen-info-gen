import java.util.Scanner;

public class VideoSource {
    private String source, container, resolution, scan, framerate, vidBitrate, aspectRatio, audCodec, numAudChannels, audSampleRate, audBitrate, duration, location, videographer;

    public VideoSource() {}

    private static String promptData(String type, Scanner scan) {
        System.out.print("Enter the " + type + ": ");
        return scan.nextLine().trim();
    }

    public void setSourceData(Scanner scan) {
        this.source = promptData("source [PRO/AUD]", scan);
        this.container = promptData("file container", scan);
        this.resolution = promptData("video resolution", scan);
        this.scan = promptData("scan", scan);
        this.framerate = promptData("frame rate", scan);
        this.vidBitrate = promptData("bitrate (MB/s)", scan);
        this.aspectRatio = promptData("aspect ratio", scan);
        this.audCodec = promptData("audio codec", scan);
        this.numAudChannels = promptData("number of audio channels", scan);
        this.audSampleRate = promptData("audio sampling rate", scan);
        this.audBitrate = promptData("audio bitrate (kbps)", scan);
        this.duration = promptData("duration", scan);
        this.location = promptData("location of recording", scan);
        this.videographer = promptData("videographer's name", scan);
    }
}
