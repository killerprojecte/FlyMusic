package flyproject.flymusic;


public interface MusicSource {

    public MusicInfo get(String keyword) throws Exception;

    public default boolean isVisible() {
        return true;
    };
}