package dawanda.de.dawandasample.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by natashalotra on 2017/11/06.
 */

public class Product implements Serializable {
    @SerializedName("id")
    private long id;

    @SerializedName("title")
    private String title;

    @SerializedName("badge")
    private String badge;

    @SerializedName("price")
    private Price price;

    @SerializedName("shop")
    private Shop shop;

    @SerializedName("default_image")
    private DefaultImage defaultImage;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public DefaultImage getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(DefaultImage defaultImage) {
        this.defaultImage = defaultImage;
    }
}
