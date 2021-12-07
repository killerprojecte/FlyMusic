package flyproject.flymusic;

import com.google.gson.Gson;
import ltd.icecold.orange.netease.api.NeteaseSearchAPI;
import ltd.icecold.orange.netease.api.NeteaseSongAPI;
import ltd.icecold.orange.netease.api.NeteaseUserAPI;
import ltd.icecold.orange.netease.bean.NeteaseResponseBody;
import me.albert.amazingbot.shaded.kotlinx.serialization.json.Json;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;


public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("API运作调试系统 输入 网易 进入网易云音乐调试\n" +
                "输入 QQ 进入QQ音乐调试");
        String status = sc.next();
        if (status.equals("网易")){
            System.out.println("输入网易云邮箱");
            String username = sc.next();
            System.out.println("输入密码");
            String password = sc.next();
            System.out.println("登陆中");
            String passwordmd5 = getMD5Str(password);
            NeteaseUserAPI neteaseUserAPI = new NeteaseUserAPI();
            neteaseUserAPI.login(username,passwordmd5);
            NeteaseSearchAPI nsa = new NeteaseSearchAPI();
            System.out.println("输入曲名");
            NeteaseResponseBody test1 = nsa.search(sc.next(),1,1,0,neteaseUserAPI.getCookie());
            int songid = JsonApi.getSongID(test1.getBody());
            if (NeteaseSongAPI.checkMusic(String.valueOf(songid),neteaseUserAPI.getCookie())){
                String urljson = NeteaseSongAPI.musicUrl(String.valueOf(songid),"999000",neteaseUserAPI.getCookie()).getBody();
                String url = JsonApi.getSongURL(urljson);
                System.out.println(url);
                String picjson = getJson.get("https://netemapi.vercel.app/song/detail?ids=" + songid);
                String pic = JsonApi.getSongPic(picjson);
                System.out.println(pic);
                String art = JsonApi.getSongArt(picjson);
                String name = JsonApi.getSongName(picjson);
                System.out.println(art);
                System.out.println(name);
            }
        } else {
        QQApi aapi = new QQApi();
        try {
            System.out.println("输入歌曲名称");
            MusicInfo musicInfo = aapi.get(sc.next());
            System.out.println(musicInfo.title);
            System.out.println(musicInfo.desc);
            System.out.println(musicInfo.purl);
            System.out.println(musicInfo.murl);
            System.out.println(musicInfo.jurl);
            System.out.println(musicInfo.icon);
            System.out.println(musicInfo.appid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        }



    }
    public static String getMD5Str(String str) {
        byte[] digest = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            digest  = md5.digest(str.getBytes("utf-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //16是表示转换为16进制数
        String md5Str = new BigInteger(1, digest).toString(16);
        return md5Str;
    }
}
