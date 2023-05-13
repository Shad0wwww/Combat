package dk.yzhy.utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ActionBar{
    private static final String version = "net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().substring(23);
   public static void sendActionbar(Player player, String message) {

        Constructor<?> constructor;
        Object a, packet = null;

        try {
            final Class<?> baseComponentClass = Class.forName(version + ".IChatBaseComponent");
            constructor = Class.forName(version + ".PacketPlayOutChat").getConstructor(baseComponentClass, byte.class);
            a = baseComponentClass.getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + message + "\"}");
            packet = constructor.newInstance(a, (byte) 2);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException ex) {
            ex.printStackTrace();
        }

        try {
            final Object entity = player.getClass().getMethod("getHandle").invoke(player);
            final Object playerConnection = entity.getClass().getField("playerConnection").get(entity);
            playerConnection.getClass().getMethod("sendPacket", Class.forName(version + ".Packet")).invoke(playerConnection, packet);
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException ex) {
            ex.printStackTrace();
        }
    }
}
