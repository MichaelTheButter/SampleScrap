package Downloader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Downloader {
    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("https://www.machineseeker.com/Milling-machines/ci-218").get();
        Elements h2Elements = document.select("h5");
        for (Element h2: h2Elements) {
            String h2Text = h2.text();
            System.out.println(h2Text);
        }
    }
}
