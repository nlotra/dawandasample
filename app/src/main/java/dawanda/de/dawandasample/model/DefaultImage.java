package dawanda.de.dawandasample.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by natashalotra on 2017/11/10.
 */

public class DefaultImage implements Serializable {
    @SerializedName("product_l")
    private String productL;

    @SerializedName("square_130")
    private String square130;

    @SerializedName("listview_l")
    private String listviewL;

    public String getProductL() {
        return productL;
    }

    public void setProductL(String productL) {
        this.productL = productL;
    }

    public String getSquare130() {
        return square130;
    }

    public void setSquare130(String square130) {
        this.square130 = square130;
    }

    public String getListviewL() {
        return listviewL;
    }

    public void setListviewL(String listviewL) {
        this.listviewL = listviewL;
    }
}
