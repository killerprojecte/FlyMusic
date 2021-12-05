package flyproject.flymusic;

public class Test {
    public static void main(String[] args) {
        String json = getJson.get("https://netemapi.vercel.app/search?keywords=绿色limit=1?type=1?offset=1\n");
        String key = "result.songs.id";
        String output = JsonApi.getString(json,key);
        System.out.println("Song ID: " + output);
    }
}
