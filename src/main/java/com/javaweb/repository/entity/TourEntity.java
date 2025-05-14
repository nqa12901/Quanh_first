package com.javaweb.repository.entity;

import java.util.List;

public class TourEntity {
    private int tour_id;
    private String address;
    private String tour_name;
    private String description;
    private String image_url;
    private int price;
    private String duration;
    private int rating;
    private List<String> poi_name;
    public int getTour_id() {
        return tour_id;
    }

    public List<String> getPoi_name() {
        return poi_name;
    }

    public void setPoi_name(List<String> poi_name) {
        this.poi_name = poi_name;
    }

    public void setTour_id(int tour_id) {
        this.tour_id = tour_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTour_name() {
        return tour_name;
    }

    public void setTour_name(String tour_name) {
        this.tour_name = tour_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
