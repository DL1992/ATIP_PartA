package Server;

import java.io.*;
import java.util.Properties;

/**
 * This class is a properties class for servers.
 * the purpose of this class in to enable the use of properties file in their strategy.
 *
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public class properties {
    public static void main(String[] args) {
        Properties properties = new Properties();
        OutputStream outToFile = null;
        try {
            outToFile = new FileOutputStream("src/config.properties");
            properties.setProperty("ServerMazeGenerateAlgo", "MyMazeGenerator");
            properties.setProperty("ServerSolveMazeAlgo", "BreadthFirstSearch");
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

    /**
     * this static function return to the server the right Maze Generator class according to the properties file.
     *
     * @return the name of the correct maze generator algorithm.
     */
    public static String getServerMazeGenerateAlgo() {
        String ServerMazeGenerateAlgo = loadProperty("ServerMazeGenerateAlgo");
        return ServerMazeGenerateAlgo;
    }

    /**
     * this static function return to the server the right search algorithm class according to the properties file.
     *
     * @return the name of the correct searching algorithm.
     */
    public static String getServerSolveMazeAlgo() {
        String ServerSolveMazeAlgo = loadProperty("ServerSolveMazeAlgo");
        return ServerSolveMazeAlgo;
    }

    /**
     * this static function return to the server the right  number of threads to use in the thread pool
     * according to the properties file.
     *
     * @return the number of threads to use.
     */
    public static String getServerThreadPoolCount() {
        String threadpoolCount = loadProperty("ServerThreadPoolCount");
        return threadpoolCount;
    }

    /**
     * this static function is used by servers to change the properties file.
     * it uses the Properties class of java to load the "config" file.
     * close all streams in the end.
     *
     * @param propKey   is the key of the property we want to change.
     * @param propValue is the new value of property.
     */
    public static void setProperty(String propKey, String propValue) {
        Properties properties = new Properties();
        OutputStream outToFile = null;
        InputStream inFromFile;
        try {
            String fileName = "config.properties";
            inFromFile = properties.class.getClassLoader().getResourceAsStream(fileName);
            properties.load(inFromFile);
            outToFile = new FileOutputStream("src/config.properties");
            properties.setProperty(propKey, propValue);
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

    /**
     * helper function for all the get methods. uses the Properties class of java
     * to open the config file and return the right property.
     * close all streams in the end.
     *
     * @param propKey is the key of the property we want to get.
     * @return the string representing the value of the key.
     */
    private static String loadProperty(String propKey) {
        Properties properties = new Properties();
        InputStream inFromFile = null;
        String returnValue = null;
        try {
            String fileName = "config.properties";
            inFromFile = properties.class.getClassLoader().getResourceAsStream(fileName);
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
