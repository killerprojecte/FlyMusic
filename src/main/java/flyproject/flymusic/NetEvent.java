package flyproject.flymusic;

import ltd.icecold.orange.netease.NeteaseCrypto;
import ltd.icecold.orange.netease.NeteaseRequest;
import ltd.icecold.orange.netease.api.NeteaseSearchAPI;
import ltd.icecold.orange.netease.api.NeteaseSongAPI;
import ltd.icecold.orange.netease.bean.NeteaseRequestOptions;
import ltd.icecold.orange.netease.bean.NeteaseResponseBody;
import ltd.icecold.orange.network.Request;
import me.albert.amazingbot.events.GroupMessageEvent;
import me.albert.amazingbot.events.PrivateMessageEvent;
import net.mamoe.mirai.message.data.MusicKind;
import net.mamoe.mirai.message.data.MusicShare;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import static flyproject.flymusic.FlyMusic.cookie;

public class NetEvent implements Listener {
    public static NeteaseResponseBody getShareLink(String id,String s,Map<String,String> cookie){
        Map<String, String> data = new HashMap<>();
        data.put("id", id);
        data.put("n", "100000");
        data.put("s", s);
        NeteaseRequestOptions requestOptions = new NeteaseRequestOptions("https://music.163.com/api/v6/playlist/detail", NeteaseCrypto.CryptoType.LINUXAPI, cookie, Request.UserAgentType.PC);
        return NeteaseRequest.postRequest(requestOptions, data);
    }
    @EventHandler
    public void onGroupMessage(GroupMessageEvent event){
        if (event.getMsg().startsWith("点歌 ")){
            NeteaseSearchAPI nsa = new NeteaseSearchAPI();
            String arg = event.getMsg().replace("点歌 ","");
            NeteaseResponseBody song = nsa.search(arg,1,1,0,cookie);
            int songid = JsonApi.getSongID(song.getBody());
            if (NeteaseSongAPI.checkMusic(String.valueOf(songid),cookie)){
                String urljson = NeteaseSongAPI.musicUrl(String.valueOf(songid),"999000",cookie).getBody();
                String url = JsonApi.getSongURL(urljson);
                String picjson = getJson.get("http://music.163.com/api/v3/song/detail?id=" + songid + "&c=[{%22id%22:%22" + songid + "%22}]");
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
    @EventHandler
    public void onPriv(PrivateMessageEvent event) {
        if (event.getMsg().startsWith("点歌 ")){
            NeteaseSearchAPI nsa = new NeteaseSearchAPI();
            String arg = event.getMsg().replace("点歌 ","");
            NeteaseResponseBody song = nsa.search(arg,1,1,0,cookie);
            int songid = JsonApi.getSongID(song.getBody());
            if (NeteaseSongAPI.checkMusic(String.valueOf(songid),cookie)){
                String urljson = NeteaseSongAPI.musicUrl(String.valueOf(songid),"999000",cookie).getBody();
                String url = JsonApi.getSongURL(urljson);
                String picjson = getJson.get("http://music.163.com/api/v3/song/detail?id=" + songid + "&c=[{%22id%22:%22" + songid + "%22}]");
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
