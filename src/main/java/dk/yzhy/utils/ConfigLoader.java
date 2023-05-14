package dk.yzhy.utils;

import dk.yzhy.Combat;
import org.bukkit.ChatColor;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ConfigLoader {
    static HashMap<String, String[]> messages;
    public static void loadALL() {
        messages = new HashMap<>();
        for (String path : Combat.mainConfigYML.getKeys(true)) {
            if (!Combat.mainConfigYML.isConfigurationSection(path)) {
                if(Combat.mainConfigYML.getStringList(path) != null && Combat.mainConfigYML.isList(path)) {
                    List<String> stringList = Combat.mainConfigYML.getStringList(path);
                    messages.put(path, stringList.toArray(new String[0]));
                    continue;
                }

                if(Combat.mainConfigYML.getString(path) != null) {
                    List<String> stringList = Collections.singletonList(Combat.mainConfigYML.getString(path));
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
