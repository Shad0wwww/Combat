package dk.yzhy.utils;

import dk.yzhy.Main;
import org.bukkit.ChatColor;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ConfigLoader {
    static HashMap<String, String> messages;
    public static void loadALL() {
        messages = new HashMap<>();
        for (String path : Main.mainConfigYML.getKeys(true)) {
            if (!Main.mainConfigYML.isConfigurationSection(path)) {
                if(Main.mainConfigYML.getStringList(path) != null && Main.mainConfigYML.isList(path)) {
                    messages.put(path, FromList(Main.mainConfigYML.getStringList(path)));
                    continue;
                }
                if(Main.mainConfigYML.getString(path) != null) {
                    messages.put(path, FromString(Main.mainConfigYML.getString(path)));
                }
            }
        }
    }
    public static String getString(String path) {
        if(messages.containsKey(path)){
            return messages.get(path);
        }
        return "";
    }
    public static Integer getInt(String path) {
        if(messages.containsKey(path)){
            return Integer.valueOf(messages.get(path));
        }
        return 0;
    }
    public static String FromList(List<String> s) {
        StringBuilder sb = new StringBuilder();
        for (Object element : s) {
            if (element instanceof List) {
                List<String> subList = (List<String>) element;
                sb.append(String.join("\n", subList));
            } else {
                sb.append(element.toString());
            }
            sb.append("\n");
        }
        return ChatColor.translateAlternateColorCodes('&', String.valueOf(sb));
    }
    public static String FromString(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
