package flyproject.flymusic;

import me.albert.amazingbot.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.MusicKind;
import net.mamoe.mirai.message.data.MusicShare;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class KugouEvent implements Listener {
    @EventHandler
    public void onGroup(GroupMessageEvent event) {
        if (event.getMsg().startsWith("点歌 ")) {
            String arg = event.getMsg().replace("点歌 ", "");
            KugouApi aapi = new KugouApi();
            System.out.println(arg);
            try {
                MusicInfo musicInfo = aapi.get(arg);
                event.response(new MusicShare(
                        MusicKind.KugouMusic,
                        musicInfo.title,
                        musicInfo.desc + "- FlyMusic",
                        musicInfo.jurl,
                        musicInfo.purl,
                        musicInfo.murl
                ));
            } catch (Exception e) {
                event.response("歌曲不存在");
            }

        }
    }
}
