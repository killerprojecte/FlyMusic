package flyproject.flymusic.miraimc;

import flyproject.flymusic.MusicInfo;
import flyproject.flymusic.QQApi;
import me.dreamvoid.miraimc.api.MiraiBot;
import me.dreamvoid.miraimc.bukkit.event.MiraiFriendMessageEvent;
import me.dreamvoid.miraimc.bukkit.event.MiraiGroupMessageEvent;
import net.mamoe.mirai.message.data.MusicKind;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MQQEvent implements Listener {
    @EventHandler
    public void onGroup(MiraiGroupMessageEvent event) {
        String msg = event.getMessage();
        if (msg.startsWith("点歌 ")) {
            String arg = msg.replace("点歌 ", "");
            QQApi aapi = new QQApi();
            try {
                MusicInfo musicInfo = aapi.get(arg);
                MiraiBot.getBot(event.getBotID()).getGroup(event.getGroupID()).sendMusicShare(
                        MusicKind.QQMusic.toString(),
                        musicInfo.title,
                        musicInfo.desc + "- FlyMusic",
                        musicInfo.jurl,
                        musicInfo.purl,
                        musicInfo.murl
                );
            } catch (Exception e) {
                MiraiBot.getBot(event.getBotID()).getGroup(event.getGroupID()).sendMessage("歌曲不存在");
            }

        }
    }
    @EventHandler
    public void onPriv(MiraiFriendMessageEvent event){
        String msg = event.getMessage();
        if (msg.startsWith("点歌 ")) {
            String arg = msg.replace("点歌 ", "");
            QQApi aapi = new QQApi();
            try {
                MusicInfo musicInfo = aapi.get(arg);
                MiraiBot.getBot(event.getBotID()).getFriend(event.getSenderID()).sendMusicShare(
                        MusicKind.QQMusic.toString(),
                        musicInfo.title,
                        musicInfo.desc + "- FlyMusic",
                        musicInfo.jurl,
                        musicInfo.purl,
                        musicInfo.murl
                );
            } catch (Exception e) {
                MiraiBot.getBot(event.getBotID()).getFriend(event.getSenderID()).sendMessage("歌曲不存在");
            }

        }
    }
}
