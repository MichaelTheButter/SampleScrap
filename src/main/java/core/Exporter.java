package core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Prepares and exports data to CSV file
 */
public class Exporter {
    /** CSV file name for data export  */
    private static final String FILE_NAME = "MachineList.csv";
    private static final String CSV_HEADER = "Name, Condition, Manufacture Year, Price\n";

    /**
     * Exports data to CSV file
     * @param machineList list of machines to export
     */
    public static void csvExport(List<Machine> machineList) {
        // get current directory and separator to create a file path
        String currentDir = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");

        List<String> stringCSVList = csvPrepare(machineList);

        File csvFile = new File(currentDir + separator + FILE_NAME);
        try (
                FileWriter fileWriter = new FileWriter(csvFile);
                ){
            for (String line: stringCSVList){
              fileWriter.write(line);
            }
        } catch (IOException e){
            System.err.println("Failed to save a CSV file");
        }
    }

    /**
     * Converts list of machine into list of CSV Strings
     * @param machineList list of machines to prepare
     * @return list of CSV Strings
     */
    static List<String> csvPrepare(List<Machine> machineList) {
        List<String> stringCSVList = new ArrayList<>();

        Function<String, String> prepareStringToCsv = x -> "\"" + x.replaceAll("\"", "\"\"") + "\",";
        Function<Integer, String> prepareIntToCSV = x -> "\"" + x + "\",";

        stringCSVList.add(CSV_HEADER);
        for (Machine machine: machineList){
            StringBuilder line = new StringBuilder();

            line.append(prepareStringToCsv.apply(machine.getName()));
            line.append(prepareStringToCsv.apply(machine.getCondition()));
            line.append(prepareIntToCSV.apply(machine.getProductionYear()));
            line.append(prepareIntToCSV.apply(machine.getPrice()));

            line.append("\n");
            stringCSVList.add(line.toString());
        }
        return stringCSVList;
    }
}
