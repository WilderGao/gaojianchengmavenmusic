package model;

/**
 * @author Administrator
 * @time：
 * @Discription：
 */
public class WishModel {
    private int wishId;
    private int customerId;
    private String songName;
    private String singerName;
    private String albumName;
    private String customerName;
    private int wishState;

    public int getWishId() {
        return wishId;
    }

    public void setWishId(int wishId) {
        this.wishId = wishId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "WishModel{" +
                "wishId=" + wishId +
                ", customerId=" + customerId +
                ", songName='" + songName + '\'' +
                ", singerName='" + singerName + '\'' +
                ", albumName='" + albumName + '\'' +
                ", customerName='" + customerName + '\'' +
                ", wishState=" + wishState +
                '}';
    }
}
