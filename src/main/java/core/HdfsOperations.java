package core;

import configuration.Config;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * A collection of file-processing methods for HDFS
 */
public class HdfsOperations {

    private static FileSystem fs;
    private static String currentDate = LocalDateTime.now().toLocalDate().toString();
    private String separator;
    private String directory;

    /**
     * Set configuration for HDFS based on Config file with NameNode IP and Port.
     * @throws IOException
     */
    public HdfsOperations() throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", Config.getNameNodeIPAndPort());
        fs = FileSystem.get(conf);
        separator = System.getProperty("file.separator");
        directory = fs.getWorkingDirectory()+separator+Config.getDirectoryName();
    }


    /**
     * Creates directory specify in Config File
     * @throws IOException
     */
    public void createDirectory() throws IOException{
        Path dir = new Path(directory);0
        fs.mkdirs(dir);
    }

    /**
     * Creates a file in HDFS and saves list of machines as a CSV file. The file name is the date the data was downloaded.
     * @param machineList List of machines to save
     * @throws IOException
     */
    public void saveFile(List<Machine> machineList) throws IOException {
        List<String> stringCSVList = Exporter.csvPrepare(machineList);
        Path filePath = new Path(directory+separator+currentDate);
        FSDataOutputStream file = fs.create(filePath);

        for (String line: stringCSVList) {
            file.writeChars(line);
        }
    }
}
