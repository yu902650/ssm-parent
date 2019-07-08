package com.example.wechar.util;


import com.example.wechar.domain.common.Storage;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;


/**
 * Properties 工具类
 */
public class PropertiesUtil {

    Properties props = null;
    //静态工厂方法


    public void readProperties(InputStream inputStream) {

        try {
            if (props == null) {
                props = new Properties();
            }
            props.load(inputStream);
            Enumeration<?> en = props.propertyNames();
            Storage constants = new Storage();
            while (en.hasMoreElements()) {
                //
                String key = (String) en.nextElement();
                String value = props.getProperty(key);
                constants.properties.put(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param key
     * @return
     */
    public static String get(String key) {
        return Storage.properties.get(key);
    }
}
