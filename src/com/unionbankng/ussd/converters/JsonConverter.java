/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unionbankng.ussd.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.ws.rs.BadRequestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author oawe
 */
public class JsonConverter {
 static Logger log = LogManager.getLogger(JsonConverter.class);
    public static <T> void toJson(OutputStream os, T obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(os, obj);
        } catch (IOException ex) {
            log.error("Failed to convert object to json : " + ex.getMessage());
            throw new BadRequestException("Failed to convert object to json");

        }
    }

    public static <T> String toJson(T obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            log.error("Failed to convert object to json : " + ex.getMessage());
        }
        return "";
    }

    ///
    public static <T> T toObj(InputStream is, Class<T> objClass) {
        T obj = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            obj = mapper.readValue(is, objClass);
        } catch (IOException ex) {
            log.error("Failed to convert json to object : " + ex.getMessage());
            throw new BadRequestException("Request message is not valid");
        }
        return obj;
    }

    public static <T> T toObj(String is, Class<T> objClass) {
        T obj = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            obj = mapper.readValue(is, objClass);
        } catch (IOException ex) {
            log.error("Failed to convert json to object : " + ex.getMessage());
        }
        return obj;
    }

}
