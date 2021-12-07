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
        System.out.println("Input Username...");
        String username = sc.next();
        System.out.println("Input Password");
        String password = sc.next();
        System.out.println("Login...");
        String passwordmd5 = getMD5Str(password);
        NeteaseUserAPI neteaseUserAPI = new NeteaseUserAPI();
        neteaseUserAPI.login(username,passwordmd5);
        NeteaseSearchAPI nsa = new NeteaseSearchAPI();
        System.out.println("Input Song name...");
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
