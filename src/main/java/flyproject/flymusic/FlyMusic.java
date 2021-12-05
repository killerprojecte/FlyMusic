package flyproject.flymusic;

import me.albert.amazingbot.events.GroupMessageEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class FlyMusic extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this,this);
        // Plugin startup logic

    }
    @EventHandler
    public void onGroupMessage(GroupMessageEvent event){
        if (event.getMsg().startsWith("点歌 ")){
            String arg = event.getMsg().replace("点歌 ","");
            getLogger().info(arg);
            String json = getJson.get("https://netemapi.vercel.app/search?keywords=" + arg + "limit=1?type=1?offset=1\n");
            getLogger().info(json);
            String key = "result.songs.id";
            String output = JsonApi.getString(json,key);
            event.response(output);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
