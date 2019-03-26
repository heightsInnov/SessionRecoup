/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unionbankng.ussd.utils;

import com.unionbankng.security.Encryptor;
import com.unionbankng.security.Encryptor.OutputFormat;
import static com.unionbankng.security.Encryptor.decryptText;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author oawe
 */
public class Config {

    private static final Properties prop = new Properties();

    static {
        Encryptor.OUTPUT_FORMAT = OutputFormat.HEX_STRING;
        Encryptor.DATA_ENCYPT_PASSWORD = "6T4tHU6utH@ye=Eduw";
        //prop = new Properties();
        try {
            try (FileInputStream f = new FileInputStream("config.properties")) {
                prop.load(f);
                Encryptor.DATA_ENCRYPT_SALT = prop.getProperty("enc.salt");
            }
        } catch (IOException ex) {
            System.out.println("failure loading config file - " + ex.getMessage());
        }
    }

    public static String getValue(String propName, boolean isEncrypted) {
        if (isEncrypted) {
            return decryptText(readValue(propName));
        } else {
            return readValue(propName);
        }
    }

    public static int getIntValue(String propName, boolean isEncrypted) {
        if (isEncrypted) {
            return Integer.parseInt(decryptText(readValue(propName)));
        } else {
            return Integer.parseInt(readValue(propName));
        }
    }

    private static String readValue(String propName) {
        return prop.getProperty(propName);
    }
}
