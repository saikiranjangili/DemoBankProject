package org.example.utilites;



import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
    Properties prop;
    File file;

    public ReadConfig() {


        file = new File("./Configurations/config.properties");

        try {
            FileInputStream fis = new FileInputStream(file);
            prop = new Properties();
            prop.load(fis);

        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
        }
    }

    public String getApplicationURL()
    {
        String url = prop.getProperty("baseURL");
        return url;
    }
    public String getUserName()
    {
        String username = prop.getProperty("username");
        return username;
    }
    public String getPassword()
    {
        String password = prop.getProperty("password");
        return password ;
    }


}
