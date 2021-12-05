package flyproject.flymusic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class getJson {
    public static String get(String checkurl){
        String str;
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(checkurl);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream(), StandardCharsets.UTF_8));
            while ((str = reader.readLine()) != null){
                sb.append(str);
                sb.append("\n");
            }
            sb.delete(sb.length() - 1,sb.length());
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private class IOException extends Throwable {
    }
}