package flyproject.flymusic;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public final class Utils {
    static boolean verbose = true;

    /**
     * Read all content from input stream.<br>
     * 从数据流读取全部数据
     *
     * @param i the input stream<br>
     *          数据流
     * @return return all read data <br>
     *         返回读入的所有数据
     * @throws IOException Signals that an I/O exception has occurred.<br>
     *                     发生IO错误
     */
    public static byte[] readAll(InputStream i) throws IOException {
        ByteArrayOutputStream ba = new ByteArrayOutputStream(16384);
        int nRead;
        byte[] data = new byte[4096];

        try {
            while ((nRead = i.read(data, 0, data.length)) != -1) {
                ba.write(data, 0, nRead);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw e;
        }

        return ba.toByteArray();
    }

    public static boolean isExistent(String urlstr) throws IOException {
        try {
            URL url = new URL(urlstr);
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            huc.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36");
            huc.setRequestMethod("HEAD");
            huc.connect();
            return huc.getResponseCode() == 200;
        } catch (Exception ex) {

            return false;
        }
    }

    public static InputStream getFromHttp(String url) throws IOException {
        try {
            HttpURLConnection huc2 = (HttpURLConnection) new URL(url).openConnection();

            huc2.setRequestMethod("GET");
            huc2.connect();
            return huc2.getInputStream();
        } catch (IOException e) {
            throw e;
        }
    }
    public static String fetchHttp(String url) throws IOException {
        try {
            HttpURLConnection huc2 = (HttpURLConnection) new URL(url).openConnection();

            huc2.setRequestMethod("GET");
            huc2.connect();
            return new String(Utils.readAll(huc2.getInputStream()),StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw e;
        }
    }
    public static int compare(String str, String target) {
        int d[][];
        int n = str.length();
        int m = target.length();
        int i;
        int j;
        char ch1;
        char ch2;
        int temp;
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        d = new int[n + 1][m + 1];
        for (i = 0; i <= n; i++) {
            d[i][0] = i;
        }
        for (j = 0; j <= m; j++) {
            d[0][j] = j;
        }
        for (i = 1; i <= n; i++) {
            ch1 = str.charAt(i - 1);
            for (j = 1; j <= m; j++) {
                ch2 = target.charAt(j - 1);
                if (ch1 == ch2 || ch1 == ch2 + 32 || ch1 + 32 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
            }
        }
        return d[n][m];
    }

    private static int min(int one, int two, int three) {
        return (one = one < two ? one : two) < three ? one : three;
    }
}