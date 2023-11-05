package core;

import configuration.Config;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


public class HdfsOperations {

    private static FileSystem fs;
    private static String currentDate = LocalDateTime.now().toLocalDate().toString();
    private String separator;
    private String directory;

    public HdfsOperations() throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", Config.getNameNodeIPAndPort());
        fs = FileSystem.get(conf);
        separator = System.getProperty("file.separator");
        directory = fs.getWorkingDirectory()+separator+Config.getDirectoryName();
    }


    public void createDirectory() throws IOException{
        Path dir = new Path(directory);
        fs.mkdirs(dir);
    }

    public void saveFile(List<Machine> machineList) throws IOException {
        List<String> stringCSVList = Exporter.csvPrepare(machineList);
        Path filePath = new Path(directory+separator+currentDate);
        FSDataOutputStream file = fs.create(filePath);

        for (String line: stringCSVList) {
            file.writeChars(line);
        }
    }
}
