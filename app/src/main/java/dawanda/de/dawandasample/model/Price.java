package dawanda.de.dawandasample.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Price implements Serializable{
    @SerializedName("currency")
    private String currency;

    @SerializedName("symbol")
    private String symbol;

    @SerializedName("cents")
    private long cents;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public long getCents() {
        return cents;
    }

    public void setCents(long cents) {
        this.cents = cents;
    }
}