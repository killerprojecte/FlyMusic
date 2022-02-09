package flyproject.flymusic.miraimc;

import flyproject.flymusic.*;
import ltd.icecold.orange.netease.api.NeteaseSearchAPI;
import ltd.icecold.orange.netease.api.NeteaseSongAPI;
import ltd.icecold.orange.netease.bean.NeteaseResponseBody;
import me.dreamvoid.miraimc.api.MiraiBot;
import me.dreamvoid.miraimc.bukkit.event.MiraiFriendMessageEvent;
import me.dreamvoid.miraimc.bukkit.event.MiraiGroupMessageEvent;
import net.mamoe.mirai.message.data.MusicKind;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static flyproject.flymusic.FlyMusic.cookie;

public class MDoubleEvent implements Listener {
    @EventHandler
    public void onGroup(MiraiGroupMessageEvent event) {
        String msg = event.getMessage();
        if (msg.startsWith("QQ ")) {
            String arg = msg.replace("QQ ", "");
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

        } else if (msg.startsWith("网易 ")){
            NeteaseSearchAPI nsa = new NeteaseSearchAPI();
            String arg = msg.replace("网易 ","");
            NeteaseResponseBody song = nsa.search(arg,1,1,0,cookie);
            int songid = JsonApi.getSongID(song.getBody());
            if (NeteaseSongAPI.checkMusic(String.valueOf(songid),cookie)){
                String urljson = NeteaseSongAPI.musicUrl(String.valueOf(songid),"999000",cookie).getBody();
                String url = JsonApi.getSongURL(urljson);
                String picjson = getJson.get("http://music.163.com/api/v3/song/detail?id=" + songid + "&c=[{%22id%22:%22" + songid + "%22}]");
                String pic = JsonApi.getSongPic(picjson);
                String art = JsonApi.getSongArt(picjson);
                String name = JsonApi.getSongName(picjson);
                MiraiBot.getBot(event.getBotID()).getGroup(event.getGroupID()).sendMusicShare(
                        MusicKind.NeteaseCloudMusic.toString(),
                        name,
                        art + "- FlyMusic",
                        "https://music.163.com/song?id=" + songid,
                        pic,
                        url
                );
            } else {
                MiraiBot.getBot(event.getBotID()).getGroup(event.getGroupID()).sendMessage("歌曲不存在");
            }

        } else if (msg.startsWith("酷狗 ")) {
            String arg = msg.replace("酷狗 ", "");
            KugouApi aapi = new KugouApi();
            
            try {
                MusicInfo musicInfo = aapi.get(arg);
                MiraiBot.getBot(event.getBotID()).getGroup(event.getGroupID()).sendMusicShare(
                        MusicKind.KugouMusic.toString(),
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
        if (msg.startsWith("QQ ")) {
            String arg = msg.replace("QQ ", "");
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

        } else if (msg.startsWith("网易 ")){
            NeteaseSearchAPI nsa = new NeteaseSearchAPI();
            String arg = msg.replace("网易 ","");
            NeteaseResponseBody song = nsa.search(arg,1,1,0,cookie);
            int songid = JsonApi.getSongID(song.getBody());
            if (NeteaseSongAPI.checkMusic(String.valueOf(songid),cookie)){
                String urljson = NeteaseSongAPI.musicUrl(String.valueOf(songid),"999000",cookie).getBody();
                String url = JsonApi.getSongURL(urljson);
                String picjson = getJson.get("http://music.163.com/api/v3/song/detail?id=" + songid + "&c=[{%22id%22:%22" + songid + "%22}]");
                String pic = JsonApi.getSongPic(picjson);
                String art = JsonApi.getSongArt(picjson);
                String name = JsonApi.getSongName(picjson);
                MiraiBot.getBot(event.getBotID()).getFriend(event.getSenderID()).sendMusicShare(
                        MusicKind.NeteaseCloudMusic.toString(),
                        name,
                        art + "- FlyMusic",
                        "https://music.163.com/song?id=" + songid,
                        pic,
                        url
                );
            } else {
                MiraiBot.getBot(event.getBotID()).getFriend(event.getSenderID()).sendMessage("歌曲不存在");
            }

        } else if (msg.startsWith("酷狗 ")) {
            String arg = msg.replace("酷狗 ", "");
            KugouApi aapi = new KugouApi();
            try {
                MusicInfo musicInfo = aapi.get(arg);
                MiraiBot.getBot(event.getBotID()).getFriend(event.getSenderID()).sendMusicShare(
                        MusicKind.KugouMusic.toString(),
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
