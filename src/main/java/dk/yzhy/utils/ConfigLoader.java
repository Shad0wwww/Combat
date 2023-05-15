package dk.yzhy.utils;

import dk.yzhy.Main;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ConfigLoader {
    static HashMap<String, String[]> messages;
    public static void loadALL() {
        messages = new HashMap<>();
        for (String path : Main.mainConfigYML.getKeys(true)) {
            if (!Main.mainConfigYML.isConfigurationSection(path)) {
                if(Main.mainConfigYML.getStringList(path) != null && Main.mainConfigYML.isList(path)) {
                    List<String> stringList = Main.mainConfigYML.getStringList(path);
                    messages.put(path, stringList.toArray(new String[0]));
                    continue;
                }
                if(Main.mainConfigYML.getString(path) != null) {
                    List<String> stringList = Collections.singletonList(Main.mainConfigYML.getString(path));
                    messages.put(path, stringList.toArray(new String[0]));
                }
            }
        }
    }
    public static String[] get(String path) {
        if(messages.containsKey(path)){
            return messages.get(path);
        }
        return new String[] { "" };
    }
    public static String getString(String path) {
        if(messages.containsKey(path)){
            return messages.get(path)[0];
        }
        return "";
    }
    public static Integer getInt(String path) {
        if(messages.containsKey(path)){
            return Integer.valueOf(messages.get(path)[0]);
        }
        return 0;
    }
}
