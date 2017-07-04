package io.keepcoding.madridshops.domain.model.Activity;

import java.io.Serializable;

public class Activity implements Serializable {

    private long id;
    private String name;
    private String description_es;
    private String description_en;
    private String address;
    private String url;
    private String imageUrl;
    private String logoUrl;
    private float latitude;
    private float longitude;

    public static Activity of(long id, String name) {
        Activity activity = new Activity();

        activity.setId(id);
        activity.setName(name);

        return activity;
    }

    private Activity() {

    }

    public long getId() {
        return id;
    }

    public Activity setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Activity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription_es() {
        return description_es;
    }

    public Activity setDescription_es(String description_es) {
        this.description_es = description_es;
        return this;
    }

    public String getDescription_en() {
        return description_en;
    }

    public Activity setDescription_en(String description_en) {
        this.description_en = description_en;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Activity setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Activity setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Activity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public Activity setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
        return this;
    }

    public float getLatitude() {
        return latitude;
    }

    public Activity setLatitude(float latitude) {
        this.latitude = latitude;
        return this;
    }

    public float getLongitude() {
        return longitude;
    }

    public Activity setLongitude(float longitude) {
        this.longitude = longitude;
        return this;
    }

}
