package Server;

import java.io.*;
import java.util.Properties;

/**
 * Created by user on 23/05/2017.
 */
public class properties {
    public static void main(String[] args) {
        Properties properties = new Properties();
        OutputStream outToFile = null;
        try {
            outToFile = new FileOutputStream("src/config.properties");
            properties.setProperty("ServerMazeGenerateAlgo", "MyMazeGenerator");
            properties.setProperty("ServerSolveMazeAlgo", /*"BestFirstSearch"*/"DepthFirstSearch");
            properties.setProperty("ServerThreadPoolCount", "7");
            properties.store(outToFile, "Author: Doron Laadan & Vladislav Sergienko");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != outToFile) {
                try {
                    outToFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static String getServerMazeGenerateAlgo() {
        String ServerMazeGenerateAlgo = loadProperty("ServerMazeGenerateAlgo");
        return ServerMazeGenerateAlgo;
    }

    public static String getServerSolveMazeAlgo() {
        String ServerSolveMazeAlgo = loadProperty("ServerSolveMazeAlgo");
        return ServerSolveMazeAlgo;
    }

    public static String getServerThreadPoolCount() {
        String threadpoolCount = loadProperty("ServerThreadPoolCount");
        return threadpoolCount;
    }

    private static String loadProperty(String propKey) {
        Properties properties = new Properties();
        InputStream inFromFile = null;
        String returnValue = null;
        try {
            String fileName = "config.properties";
            inFromFile = properties.class.getClassLoader().getResourceAsStream(fileName);
//            inFromFile = new FileInputStream("src/config.properties");
            properties.load(inFromFile);
            returnValue = properties.getProperty(propKey);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != inFromFile) {
                try {
                    inFromFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return returnValue;
        }
    }

}
