package flyproject.flymusic;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonApi {
    public static int getSongID(String str) {
                String resultStr = str;

                JsonParser jp = new JsonParser();
                //将json字符串转化成json对象
                JsonObject jo = jp.parse(resultStr).getAsJsonObject();
                int id = jo.get("result").getAsJsonObject().get("songs")
                        .getAsJsonObject().get("id").getAsInt();
                return id;
    }

}
