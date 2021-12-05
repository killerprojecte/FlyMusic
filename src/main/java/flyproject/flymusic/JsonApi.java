package flyproject.flymusic;

import com.alibaba.fastjson.JSONObject;

public class JsonApi {
    public static int getString(String str, String key) {
        String[] split = key.split("\\.");
        if (split.length > 1) {
            for (String s : split) {
                String targetKey = key.substring(key.indexOf(".") + 1);
                JSONObject jsonObject = JSONObject.parseObject(str);
                int string = jsonObject.getInteger(s);
                return jsonObject.getInteger(targetKey);
            }
        }
        // 没有"." 直接取
        JSONObject jsonObject = JSONObject.parseObject(str);
        return jsonObject.getInteger(key);
    }

}
