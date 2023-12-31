package core;

import core.*;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

/**
 * Downloads, segregates, exports and prints out a machine's data from the URL, defined in Downloader
 */
public class ProgramRun {
    public static void main(String[] args){
        Downloader importData = new Downloader();
        DataSegregation segregateData = new DataSegregation();

        try {
            Elements firstPage = importData.downloadFirstPageElements();
            List<Machine> offerList = segregateData.getMachines(firstPage);

            offerList.forEach(System.out::println);

            Exporter.csvExport(offerList);

            HdfsOperations hdfs = new HdfsOperations();
            hdfs.createDirectory();
            hdfs.saveFile(offerList);
        } catch (IOException e) {
            System.err.println("Failed to load data");
        }
    }
}
