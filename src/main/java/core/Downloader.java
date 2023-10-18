package core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Downloader {
    private static final String URL_ADDRESS_FIRST_PAGE = "https://www.machineseeker.com/Milling-machines/ci-218";
    private static String urlNextPages = "https://www.machineseeker.com/Milling-machines/ci-218?page=2";

    private static final String HTML_SECTION = "section.grid-body";


    public static String getUrlNextPages() {
        return urlNextPages;
    }

    public static void setUrlNextPages(String urlNextPages) {
        Downloader.urlNextPages = urlNextPages;
    }

    public Elements downloadFirstPageElements() throws IOException {
        Document document = Jsoup.connect(URL_ADDRESS_FIRST_PAGE).get();
        return document.select(HTML_SECTION);
    }

}
