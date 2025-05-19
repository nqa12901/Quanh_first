package com.javaweb.builder;

public class TourSearchBuilder {

    private String type_name;
    private String address;
    private Integer price;
    private String duration;
    private Integer rating;
    private TourSearchBuilder(Builder builder) {
        this.type_name=builder.type_name;
        this.address=builder.address;
        this.price=builder.price;
        this.duration=builder.duration;
        this.rating=builder.rating;

    }
    public String getType_name() {
        return type_name;
    }

    public String getAddress() {
        return address;
    }

    public Integer getPrice() {
        return price;
    }

    public String getDuration() {
        return duration;
    }

    public Integer getRating() {
        return rating;
    }
    public static class Builder {
        private String type_name;
        private String address;
        private Integer price;
        private String duration;
        private Integer rating;

        public Builder setType_name(String type_name) {
            this.type_name = type_name;
            return this;

        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setPrice(Integer price) {
            this.price = price;
            return this;
        }

        public Builder setDuration(String duration) {
            this.duration = duration;
            return this;
        }

        public Builder setRating(Integer rating) {
            this.rating = rating;
            return this;
        }
        public TourSearchBuilder build() {
            return new TourSearchBuilder(this);
        }
    }
}
