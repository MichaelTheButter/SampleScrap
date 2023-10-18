package core;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * This class segregates a data  from HTML element
 */
public class DataSegregation {
    private static final String HTML_HEADER = "h5";
    private static final String HTML_YEAR_AND_DESCRIPTION = "div.d-flex.fs-8.gap-r-5";
    private static final String currency = "€";
    private static final String HTML_PRICE = "div.price";


    public List<Machine> getMachines(Elements elements) {
        List<Machine> machineList = new ArrayList<>();
        for (Element element: elements) {
            Machine machine = new Machine(getHeader(element), getDescription(element), getYear(element), getPrice(element));
            machineList.add(machine);
        }
        return machineList;
    }


    private String getHeader(Element element) {
        return element.select(HTML_HEADER).text();
    }
    private int getYear(Element element) {
        int year;
        if (hasYear(element)) {
            String description = element.select(HTML_YEAR_AND_DESCRIPTION).text();
            year = Integer.parseInt(description.substring(0,4));
        } else year = 0;
        return year;
    }

    private String getDescription(Element element) {
        String description = element.select(HTML_YEAR_AND_DESCRIPTION).text();
        if (hasYear(element)){
            return description.substring(4).trim();
        } else return description;
    }


    /**
     * Check if the description is long enough to contain the manufacture Year, if so, check if there is 4 digits on the beginning
     */
        private boolean hasYear(Element element) {
        String description = element.select(HTML_YEAR_AND_DESCRIPTION).text();

       if (description.length() > 4){
            int countDigits = 0;
            for(int i=0; i <4; i++){
                if(Character.isDigit(description.charAt(i))) countDigits++;
            }
            return countDigits == 4;
        } else return false;
    }

    private int getPrice(Element element) {
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        String stringPrice = element.select(HTML_PRICE).text();
        if (stringPrice.startsWith(currency)) {
            try {
                Number price = format.parse(stringPrice.substring(1));
                return price.intValue();
            } catch (ParseException e) {
                return 0;
            }
        }
        return 0;
    }
}
