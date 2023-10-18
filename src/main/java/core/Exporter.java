package core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;

/**
 * This class exports data to CSV file
 */
public class Exporter {
    /** CSV file name for data export  */
    private static final String FILE_NAME = "MachineList.csv";
    private static final String CSV_HEADER = "Name, Condition, Manufacture Year, Price\n";

    /**
     * Prepares and export data to CSV file
     * @param machineList list of machines to export
     */
    public static void csvExport(List<Machine> machineList) {
        File csvFile = new File(FILE_NAME);
        Function<String, String> prepareStringToCsv = x -> "\"" + x.replaceAll("\"", "\"\"") + "\",";
        Function<Integer, String> prepareIntToCSV = x -> "\"" + x + "\",";
        try (
                FileWriter fileWriter = new FileWriter(csvFile);

                ){
            fileWriter.write(CSV_HEADER);

            for (Machine machine: machineList){
                StringBuilder line = new StringBuilder();

                line.append(prepareStringToCsv.apply(machine.getName()));
                line.append(prepareStringToCsv.apply(machine.getCondition()));
                line.append(prepareIntToCSV.apply(machine.getProductionYear()));
                line.append(prepareIntToCSV.apply(machine.getPrice()));

                line.append("\n");
                fileWriter.write(line.toString());
            }

        } catch (IOException e){
            System.err.println("Failed to save a CSV file");
        }
    }
}
