package flyproject.flymusic;

import ltd.icecold.orange.netease.api.NeteaseSearchAPI;
import ltd.icecold.orange.netease.api.NeteaseSongAPI;
import ltd.icecold.orange.netease.bean.NeteaseResponseBody;
import me.albert.amazingbot.events.GroupMessageEvent;
import me.albert.amazingbot.events.PrivateMessageEvent;
import net.mamoe.mirai.message.data.MusicKind;
import net.mamoe.mirai.message.data.MusicShare;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static flyproject.flymusic.FlyMusic.cookie;

public class QQEvent implements Listener {
    @EventHandler
    public void onGroup(GroupMessageEvent event) {
        if (event.getMsg().startsWith("点歌 ")) {
            String arg = event.getMsg().replace("点歌 ", "");
            QQApi aapi = new QQApi();
            try {
                MusicInfo musicInfo = aapi.get(arg);
                event.response(new MusicShare(
                        MusicKind.QQMusic,
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
    @EventHandler
    public void onPriv(PrivateMessageEvent event){
        if (event.getMsg().startsWith("点歌 ")) {
            String arg = event.getMsg().replace("点歌 ", "");
            QQApi aapi = new QQApi();
            try {
                MusicInfo musicInfo = aapi.get(arg);
                event.response(new MusicShare(
                        MusicKind.QQMusic,
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
