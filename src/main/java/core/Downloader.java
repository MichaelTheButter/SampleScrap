package core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * This class defines all URL addresses and download a HTML section
 */
public class Downloader {
    /** This is a URL address for scrapping data */
    private static final String URL_ADDRESS_FIRST_PAGE = "https://www.machineseeker.com/Milling-machines/ci-218";
    private static String urlNextPages = "https://www.machineseeker.com/Milling-machines/ci-218?page=2";

    /** HTML section with all machines data */
    private static final String HTML_SECTION = "section.grid-body";


    public static String getUrlNextPages() {
        return urlNextPages;
    }

    public static void setUrlNextPages(String urlNextPages) {
        Downloader.urlNextPages = urlNextPages;
    }

    /**
     * Connects with URL and sorts out a section with machine data
     * @return              HTML section
     * @throws IOException  connection fails
     */
    public Elements downloadFirstPageElements() throws IOException {
        Document document = Jsoup.connect(URL_ADDRESS_FIRST_PAGE).get();
        return document.select(HTML_SECTION);
    }

}
