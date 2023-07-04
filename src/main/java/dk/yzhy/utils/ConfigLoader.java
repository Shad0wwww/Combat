package dk.yzhy.utils;

import dk.yzhy.Combat;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.List;

public class ConfigLoader {
    static HashMap<String, String> messages;
    public static void loadALL() {
        messages = new HashMap<>();
        for (String path : Combat.mainConfigYML.getKeys(true)) {
            if (!Combat.mainConfigYML.isConfigurationSection(path)) {
                if(Combat.mainConfigYML.getStringList(path) != null && Combat.mainConfigYML.isList(path)) {
                    messages.put(path, FromList(Combat.mainConfigYML.getStringList(path)));
                    continue;
                }
                if(Combat.mainConfigYML.getString(path) != null) {
                    messages.put(path, FromString(Combat.mainConfigYML.getString(path)));
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
        return 1;
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
