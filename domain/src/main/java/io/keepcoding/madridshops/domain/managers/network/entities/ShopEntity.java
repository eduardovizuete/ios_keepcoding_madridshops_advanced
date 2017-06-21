package io.keepcoding.madridshops.domain.managers.network.entities;

import com.google.gson.annotations.SerializedName;

public class ShopEntity {

    @SerializedName("id") private long id;
    @SerializedName("name") private String name;
    @SerializedName("address") private String address;
    @SerializedName("description_es") private String description_es;
    @SerializedName("gps_lat") private String gps_lat;
    @SerializedName("gps_long") private String gps_long;
    @SerializedName("img") private String img;
    @SerializedName("url") private String url;
    @SerializedName("telephone") private String telephone;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription_es() {
        return description_es;
    }

    public String getGps_lat() {
        return gps_lat;
    }

    public String getGps_long() {
        return gps_long;
    }

    public String getImg() {
        return img;
    }

    public String getUrl() {
        return url;
    }

    public String getTelephone() {
        return telephone;
    }
}
