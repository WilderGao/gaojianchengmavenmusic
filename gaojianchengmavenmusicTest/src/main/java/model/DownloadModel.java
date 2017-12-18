package model;

/**
 * @author Administrator
 * @time：
 * @Discription：
 */
public class DownloadModel {
    private long customerId;
    private String singerName;
    private String songName;
    private String albumName;
    private String imgUrl;
    private String playUrl;
    private String singerUrl;

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public String getSingerUrl() {
        return singerUrl;
    }

    public void setSingerUrl(String singerUrl) {
        this.singerUrl = singerUrl;
    }

    @Override
    public String toString() {
        return "DownloadModel{" +
                "customerId=" + customerId +
                ", singerName='" + singerName + '\'' +
                ", songName='" + songName + '\'' +
                ", albumName='" + albumName + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", playUrl='" + playUrl + '\'' +
                ", singerUrl='" + singerUrl + '\'' +
                '}';
    }
}
