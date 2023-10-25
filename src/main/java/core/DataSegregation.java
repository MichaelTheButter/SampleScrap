package core;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Segregates data from HTML section and puts it into a list of machines
 */
public class DataSegregation {
    private static final String HTML_HEADER = "h5";
    private static final String HTML_YEAR_AND_DESCRIPTION = "div.d-flex.fs-8.gap-r-5";

    /** Local currency standard for EU, it could be different in other locations */
    private static final String currency = "€";
    private static final String HTML_PRICE = "div.price";


    /**
     * Creates new machine with cleaned data for each HTML element and add it to the machine List
     * @param elements  array of all elements from HTML section
     * @return          the machine List from HTML section
     */
    public List<Machine> getMachines(Elements elements) {
        List<Machine> machineList = new ArrayList<>();
        for (Element element: elements) {
            Machine machine = new Machine(getHeader(element), getDescription(element), getYear(element), getPrice(element));
            machineList.add(machine);
        }
        return machineList;
    }


    /**
     * Gets machine name and type from a HTML header
     * @param element   element from HTML section
     * @return          header from an element, which is a machine name and type
     */
    private String getHeader(Element element) {
        return element.select(HTML_HEADER).text();
    }

    /**
     * Separates a year of manufacture from the description if there is one
     * @param element   element from HTML section
     * @return          a year, if there is one, if not return 0
     */
    private int getYear(Element element) {
        int year;
        if (hasYear(element)) {
            String description = element.select(HTML_YEAR_AND_DESCRIPTION).text();
            year = Integer.parseInt(description.substring(0,4));
        } else year = 0;
        return year;
    }

    /**
     * Gets a description without year of manufacture
     * @param element   element from HTML sections
     * @return          description of a machine's condition
     */
    private String getDescription(Element element) {
        String description = element.select(HTML_YEAR_AND_DESCRIPTION).text();
        if (hasYear(element)){
            return description.substring(4).trim();
        } else return description;
    }


    /**
     * Check if the description is long enough to contain year of manufacture, if so, check if there is 4 digits at the beginning which stands for year of manufacture
     * @param element   element from HTML section
     * @return          boolean if there is a year in description or not
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

    /**
     * Separates a price from HTML section
     * @param element   element from HTML section
     * @return          price or if there is no price it returns 0
     */
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
