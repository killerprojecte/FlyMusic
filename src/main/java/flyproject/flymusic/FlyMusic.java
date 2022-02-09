
package flyproject.flymusic;


import flyproject.flymusic.miraimc.MDoubleEvent;
import flyproject.flymusic.miraimc.MKugouEvent;
import flyproject.flymusic.miraimc.MNetEvent;
import flyproject.flymusic.miraimc.MQQEvent;
import ltd.icecold.orange.netease.api.NeteaseUserAPI;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
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
                "(c) Copyright 2022 FlyProject" +
                "§c§lThis Project Use GPLv3 Open Source License\n" +
                "§c§lPlease respect the copyright of the author\n" +
                "§e[Only published on MCBBS forum and Github]");
        saveDefaultConfig();
        //登陆网易云账号
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
        getLogger().warning("正在检测服务器已安装的框架 本插件默认优先使用AmazingBot");
        if (Bukkit.getPluginManager().getPlugin("AmazingBot")!=null){ //注册AmazingBot
            getLogger().info("检测到服务器使用AmazingBot框架 正在注册事件");
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
        } else if (Bukkit.getPluginManager().getPlugin("MiraiMC")!=null){ //注册MiraiMC - 2.0.0
            getLogger().info("检测到服务器使用MiraiMC框架 正在注册事件");
            if (getConfig().getString("source").equals("net")){
                Bukkit.getPluginManager().registerEvents(new MNetEvent(),this);
                getLogger().info("登录成功");
            } else if (getConfig().getString("source").equals("qq")){
                Bukkit.getPluginManager().registerEvents(new MQQEvent(),this);
            } else if (getConfig().getString("source").equals("kugou")){
                Bukkit.getPluginManager().registerEvents(new MKugouEvent(),this);
            } else {
                getLogger().info("请检查配置文件!");
                onDisable();
                return;
            }
            Bukkit.getPluginManager().registerEvents(new MDoubleEvent(),this);
        } else {
            getLogger().warning("没有检测到支持的框架 无法启动");
            onDisable();
            return;
        }
        //初始化完成
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

