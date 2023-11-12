package configuration;

public class Config {
    private static String nameNodeIPAndPort = "type your IP and port here";
    private static String directoryName = "type a directory";

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
