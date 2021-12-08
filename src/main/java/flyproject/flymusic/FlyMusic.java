
package flyproject.flymusic;

import ltd.icecold.orange.netease.api.NeteaseSearchAPI;
import ltd.icecold.orange.netease.api.NeteaseSongAPI;
import ltd.icecold.orange.netease.api.NeteaseUserAPI;
import ltd.icecold.orange.netease.bean.NeteaseResponseBody;
import me.albert.amazingbot.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.MusicKind;
import net.mamoe.mirai.message.data.MusicShare;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public final class FlyMusic extends JavaPlugin implements Listener {
    public static Map<String,String> cookie = new HashMap<>();

    @Override
    public void onEnable() {
        getLogger().info("\n    ________      __  ___           _     \n" +
                "   / ____/ /_  __/  |/  /_  _______(_)____\n" +
                "  / /_  / / / / / /|_/ / / / / ___/ / ___/\n" +
                " / __/ / / /_/ / /  / / /_/ (__  ) / /__  \n" +
                "/_/   /_/\\__, /_/  /_/\\__,_/____/_/\\___/  \n" +
                "        /____/                            \n\n" +
                "(c) Copyright 2021 FlyProject" +
                "§c§lThis Project Use GPLv3 Open Source License\n" +
                "§c§lPlease respect the copyright of the author\n" +
                "§e[Only published on MCBBS forum and Github]");
        saveDefaultConfig();
        if (getConfig().getString("net.type").equals("mail")){
            String username = getConfig().getString("net.username");
            String password = getConfig().getString("net.password");
            String passwordmd5 = getMD5Str(password);
            NeteaseUserAPI neteaseUserAPI = new NeteaseUserAPI();
            neteaseUserAPI.login(username,passwordmd5);
            cookie = neteaseUserAPI.getCookie();
        } else if (getConfig().getString("net.type").equals("net.phone")){
            String username = getConfig().getString("net.username");
            String password = getConfig().getString("net.password");
            String passwordmd5 = getMD5Str(password);
            NeteaseUserAPI neteaseUserAPI = new NeteaseUserAPI();
            neteaseUserAPI.loginPhone(username,passwordmd5);
            cookie = neteaseUserAPI.getCookie();
        } else {
            getLogger().info("登录失败");
        }
        if (getConfig().getString("source").equals("net")){
            Bukkit.getPluginManager().registerEvents(new NetEvent(),this);
            getLogger().info("登录成功");
        } else if (getConfig().getString("source").equals("qq")){
            Bukkit.getPluginManager().registerEvents(new QQEvent(),this);
        } else if (getConfig().getString("source").equals("kugou")){
            Bukkit.getPluginManager().registerEvents(new KugouEvent(),this);
        } else {
            getLogger().info("请检查配置文件!");
            onDisable();
            return;
        }
        Bukkit.getPluginManager().registerEvents(new DoubleEvent(),this);

        // Plugin startup logic

    }
    public static String getMD5Str(String str) {
        byte[] digest = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            digest  = md5.digest(str.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String md5Str = new BigInteger(1, digest).toString(16);
        return md5Str;
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

