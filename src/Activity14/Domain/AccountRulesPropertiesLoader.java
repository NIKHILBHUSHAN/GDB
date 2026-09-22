package Activity14.Domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class AccountRulesPropertiesLoader {
    private Properties properties = new Properties();

    public AccountRulesPropertiesLoader(String configPath) {
        loadProperties(configPath);
    }

    // TODO: Step 2.1 - Load the key=value pairs from configPath into 'properties' with properties.load(InputStream):
    //   1. Try the classpath first: getClass().getClassLoader().getResourceAsStream(configPath).
    //   2. If that returns null and new File(configPath) exists, open it with a FileInputStream instead.
    //   3. Close the stream afterwards. Catch any exception and print a warning (leave 'properties' empty).
    /*private void loadProperties(String configPath) {
        try{
            InputStream input=getClass().getClassLoader().getResourceAsStream(configPath);
            if(input==null && new File(configPath).exists()){
                input=new FileInputStream(configPath);
            }
            if (input != null) {
                try(InputStream stream=input){
                    properties.load(stream);
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading properties file"+e.getMessage());
        }
    }*/
    private void loadProperties(String configPath) {

        try {
            System.out.println("Trying to load: " + configPath);

            InputStream input =
                    getClass().getClassLoader()
                            .getResourceAsStream(configPath);

            if (input != null) {
                System.out.println("FOUND in classpath: " + configPath);
            }

            if (input == null && new File(configPath).exists()) {
                System.out.println("FOUND in filesystem: " + configPath);
                input = new FileInputStream(configPath);
            }

            if (input == null) {
                System.out.println("NOT FOUND: " + configPath);
                return;
            }

            try (InputStream stream = input) {
                properties.load(stream);
            }

            System.out.println("Loaded properties: " + properties);

        } catch (Exception e) {
            System.out.println("Error loading properties: " + e.getMessage());
        }
    }

    // TODO: Step 2.2 - Return the value stored for key, or defaultValue if the key is missing.
    public String getProperty(String key, String defaultValue) {
        /*if (properties.containsKey(key)) {
            return properties.getProperty(key);
        }
        return defaultValue;*/
        return properties.getProperty(key,defaultValue);
    }

    // TODO: Step 2.2 - Parse the value for key as a double (trim it first).
    //   Return defaultValue if the key is missing or the value is not a number (NumberFormatException).
    public double getDouble(String key, double defaultValue) {
         if (!properties.containsKey(key)) {
             return defaultValue;
         }
         try{
             return Double.parseDouble(properties.getProperty(key).trim());
         }catch (NumberFormatException e){
             return defaultValue;
         }
    }

    // TODO: Same as getDouble, but parse the value with Integer.parseInt.
    public int getInt(String key, int defaultValue) {
         if (!properties.containsKey(key)) {
             return defaultValue;
         }
         try
             {
             return Integer.parseInt(properties.getProperty(key).trim());
             }catch (NumberFormatException e){
             return defaultValue;
         }
    }
}
