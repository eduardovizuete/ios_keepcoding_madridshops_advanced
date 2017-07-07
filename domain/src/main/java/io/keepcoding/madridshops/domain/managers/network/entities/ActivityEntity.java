package io.keepcoding.madridshops.domain.managers.network.entities;

import com.google.gson.annotations.SerializedName;

public class ActivityEntity {
    @SerializedName("id") private long id;
    @SerializedName("name") private String name;
    @SerializedName("description_es") private String description_es;
    @SerializedName("description_en") private String description_en;
    @SerializedName("address") private String address;
    @SerializedName("url") private String url;
    @SerializedName("img") private String imageUrl;
    @SerializedName("logo_img") private String logoUrl;
    @SerializedName("gps_lat") private String latitude;
    @SerializedName("gps_lon") private String longitude;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription_es() {
        return description_es;
    }

    public String getDescription_en() {
        return description_en;
    }

    public String getAddress() {
        return address;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
