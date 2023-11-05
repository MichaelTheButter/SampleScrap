package configuration;

public class Config {
    private static String nameNodeIPAndPort = "hdfs://192.168.0.137:9000//";
    private static String directoryName = "MillingMachines";

    public static String getNameNodeIPAndPort() {
        return nameNodeIPAndPort;
    }

    public static void setNameNodeIPAndPort(String nameNodeIPAndPort) {
        Config.nameNodeIPAndPort = nameNodeIPAndPort;
    }

    public static String getDirectoryName() {
        return directoryName;
    }

    public static void setDirectoryName(String directoryName) {
        Config.directoryName = directoryName;
    }
}
