import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DownloaderTest {
    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("https://www.machineseeker.com/Milling-machines/ci-218").get();
        Elements h2Elements = document.select("h5");
        Elements takeYear = document.select("div.d-flex.fs-8.gap-r-5");
        Elements sections = document.select("section.grid-body");
        Integer year;
        for (Element h2: sections) {
            String aText = takeYear.text();
            String h2Text = h2.select("div.price").text();
            //try {
            //  year = Integer.valueOf(h2Text);
            //    } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            //        year = 0;
            //}
            System.out.println(h2Text);
        }
    }
}

//       for (Element element: elements){
//            String description = element.select(HTML_YEAR).text();
//            try {
//                year = Integer.valueOf(description.substring(0,4));
//            } catch (NumberFormatException e) {
//                year = 0;
//                }
//        } return year;
