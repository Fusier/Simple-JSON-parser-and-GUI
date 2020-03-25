package fi.tuni.tamk.tiko.parser;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class JsonObject has multiple attributes and methods.
 * <p>
 * JsonObject class includes multitple attributes and methods for creating JsonArray and JsonObject
 * </p>
 * @author Samu Willman
 * @version 4.0
 */
public class JsonObject {

    private Map<String, Object> hashMap;
    private int index = 0;
    private String temporary = "";
    private int firstOrSecond = 0;
    private int size;


    /**
     * Method add which creates and fills hashmap.
     * <p>
     * Method add creates new hashmap, then gets the target objects classes. Filters wanted classes
     * and returns them into a string.
     * </p>
     * @param Object obj is given to method and it is an Item.
     * @return Hashmap which has key and values.
     */
    public final Map<String, Object> add(Object obj) {
        hashMap = new HashMap<>();
        try {
            Class<? extends Object> c = obj.getClass();
            Method[] m = c.getMethods();
            for (int i = 0; i < m.length; i++) {
                if (m[i].getName().indexOf("get") == 0 && !m[i].getName().equals("getClass")) {
                    String name = m[i].getName().toLowerCase().substring(3, 4) + m[i].getName().substring(4);
                    hashMap.put(name, m[i].invoke(obj));
                }
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        return hashMap;
    }

    /**
     * Method setSize sets size for the hashmap.
     * @param int size is given to method and saved as an attribute.
     */

    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Method getSize returns the size of hashmap.
     * @return size
     */
    public int getSize() {
        return size;
    }


    /**
     * Method toString overrides default toString() and creates JsonObject
     * @return String which is in Json format.
     */
    @Override
    public String toString() {
        if (index == 0) {
            temporary = "{";
        } else {
            temporary += "{";
        }
        hashMap.entrySet().forEach(entry -> {
            if (index == 0) {
                temporary += "\"" + entry.getKey() + "\" : \"" + entry.getValue() + " \" ,";
                index++;
            } else if (firstOrSecond == 1) {
                temporary += "\"" + entry.getKey() + "\" : \"" + entry.getValue() + "\" , ";
                firstOrSecond = 0;
                index++;
            } else if (firstOrSecond == 0) {
                temporary += "\"" + entry.getKey() + "\" : \"" + entry.getValue() + "\"";
                index++;
                firstOrSecond = 1;
            }
        });
        temporary += "}";
        return temporary;
    }

    /**
     * Method toStringArray which turns hashmap into JsonArray format.
     * @return String which is in JsonArray format.
     */
    public String toStringArray() {
        if (index == 0) {
            temporary = "[{";
        } else {
            temporary += "{";
        }
        hashMap.entrySet().forEach(entry -> {
            if (index == 0) {
                temporary += "\"" + entry.getKey() + "\" : \"" + entry.getValue() + " \" ,";
                index++;
            } else if (firstOrSecond == 1) {
                temporary += "\"" + entry.getKey() + "\" : \"" + entry.getValue() + "\" , ";
                firstOrSecond = 0;
                index++;
            } else if (firstOrSecond == 0) {
                temporary += "\"" + entry.getKey() + "\" : \"" + entry.getValue() + "\"";
                index++;
                firstOrSecond = 1;
            }
        });

        if (size * 2 == index) {
            temporary += "}]";
        } else {
            temporary += "},";
        }
        return temporary;
    }
}

