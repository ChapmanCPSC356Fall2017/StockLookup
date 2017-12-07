package test.example.com.stockapplication;

import com.google.gson.annotations.SerializedName;

public class LookupResult
{
    @SerializedName("Symbol")
    public String symbol;

    @SerializedName("Name")
    public String name;

    @SerializedName("Exchange")
    public String exchange;
}
