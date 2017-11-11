package dawanda.de.dawandasample.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Seller implements Serializable {
    @SerializedName("id")
    private long id;

    @SerializedName("username")
    private String username;

    @SerializedName("rating")
    private int rating;

    @SerializedName("image_base_url")
    private String imageUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
