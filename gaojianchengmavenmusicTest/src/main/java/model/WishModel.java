package model;

/**
 * @Author:高键城
 * @time：
 * @Discription：
 */
public class WishModel {
    private int wishId;
    private String customerEmail;
    private String songName;
    private String singerName;
    private String albumName;
    private int wishState;

    public int getWishId() {
        return wishId;
    }

    public void setWishId(int wishId) {
        this.wishId = wishId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getWishState() {
        return wishState;
    }

    public void setWishState(int wishState) {
        this.wishState = wishState;
    }

    @Override
    public String toString() {
        return "WishModel{" +
                "wishId=" + wishId +
                ", customerEmail='" + customerEmail + '\'' +
                ", songName='" + songName + '\'' +
                ", singerName='" + singerName + '\'' +
                ", ablumName='" + albumName + '\'' +
                ", wishState=" + wishState +
                '}';
    }
}
