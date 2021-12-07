package flyproject.flymusic;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonApi {
    public static int getSongID(String json) {
                String resultStr = json;
                JsonParser jp = new JsonParser();
                JsonObject jo = jp.parse(resultStr).getAsJsonObject();
                int id = jo.get("result").getAsJsonObject().get("songs")
                        .getAsJsonArray().get(0).getAsJsonObject() .get("id").getAsInt();
                return id;
    }
    public static String getSongURL(String json) {
        String resultStr = json;
        JsonParser jp = new JsonParser();
        JsonObject jo = jp.parse(resultStr).getAsJsonObject();
        String id = jo.get("data").getAsJsonArray().get(0)
                .getAsJsonObject().get("url").getAsString();
        return id;
    }
    public static String getSongPic(String json) {
        String resultStr = json;
        JsonParser jp = new JsonParser();
        JsonObject jo = jp.parse(resultStr).getAsJsonObject();
        String id = jo.get("songs").getAsJsonArray().get(0).getAsJsonObject().get("al")
                .getAsJsonObject().get("picUrl")
                .getAsString();
        return id;
    }
    public static String getSongArt(String json) {
        String resultStr = json;
        JsonParser jp = new JsonParser();
        JsonObject jo = jp.parse(resultStr).getAsJsonObject();
        String id = jo.get("songs").getAsJsonArray().get(0).getAsJsonObject().get("ar")
                .getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString();
        return id;
    }
    public static String getSongName(String json) {
        String resultStr = json;
        JsonParser jp = new JsonParser();
        JsonObject jo = jp.parse(resultStr).getAsJsonObject();
        String id = jo.get("songs").getAsJsonArray().get(0)
                .getAsJsonObject().get("name").getAsString();
        return id;
    }
}
