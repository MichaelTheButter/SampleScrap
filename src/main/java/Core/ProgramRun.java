package Core;

import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class ProgramRun {
    public static void main(String[] args){
        Downloader importData = new Downloader();
        DataSegregation segregateData = new DataSegregation();

        try {
            Elements firstPage = importData.downloadFirstPageElements();
            List<Machine> offerList = segregateData.getMachines(firstPage);
            offerList.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Failed to load data");
        }
    }
}
