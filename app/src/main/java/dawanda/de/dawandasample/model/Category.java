package dawanda.de.dawandasample.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by natashalotra on 2017/11/06.
 */

public class Category implements Serializable {
    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("image_url")
    private String imageUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
