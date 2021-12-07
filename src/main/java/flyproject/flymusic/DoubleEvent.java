package flyproject.flymusic;

import ltd.icecold.orange.netease.api.NeteaseSearchAPI;
import ltd.icecold.orange.netease.api.NeteaseSongAPI;
import ltd.icecold.orange.netease.bean.NeteaseResponseBody;
import me.albert.amazingbot.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.MusicKind;
import net.mamoe.mirai.message.data.MusicShare;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static flyproject.flymusic.FlyMusic.cookie;

public class DoubleEvent implements Listener {
    @EventHandler
    public void onGroup(GroupMessageEvent event) {
        if (event.getMsg().startsWith("QQ ")) {
            String arg = event.getMsg().replace("QQ ", "");
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

        } else if (event.getMsg().startsWith("网易 ")){
            NeteaseSearchAPI nsa = new NeteaseSearchAPI();
            String arg = event.getMsg().replace("网易 ","");
            NeteaseResponseBody song = nsa.search(arg,1,1,0,cookie);
            int songid = JsonApi.getSongID(song.getBody());
            if (NeteaseSongAPI.checkMusic(String.valueOf(songid),cookie)){
                String urljson = NeteaseSongAPI.musicUrl(String.valueOf(songid),"999000",cookie).getBody();
                String url = JsonApi.getSongURL(urljson);
                String picjson = getJson.get("https://netemapi.vercel.app/song/detail?ids=" + songid);
                String pic = JsonApi.getSongPic(picjson);
                String art = JsonApi.getSongArt(picjson);
                String name = JsonApi.getSongName(picjson);
                event.response(new MusicShare(
                        MusicKind.NeteaseCloudMusic,
                        name,
                        art + "- FlyMusic",
                        "https://music.163.com/song?id=" + songid,
                        pic,
                        url
                ));
            } else {
                event.response("歌曲不存在");
            }

        }
    }
}
