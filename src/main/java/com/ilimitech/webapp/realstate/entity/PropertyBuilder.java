package com.ilimitech.webapp.realstate.entity;

public final class PropertyBuilder {
    private Long id;
    private String tittle;
    private String imageUrl;
    private String status;
    private String type;
    private Double price;
    private String address;
    private Double area;
    private Integer beds;
    private Integer baths;

    private PropertyBuilder() {
    }

    public static PropertyBuilder builder() {
        return new PropertyBuilder();
    }

    public PropertyBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public PropertyBuilder title(String title) {
        this.tittle = title;
        return this;
    }
    public PropertyBuilder imageName(String imageName) {
        this.imageUrl = imageName;
        return this;
    }

    public PropertyBuilder status(String status) {
        this.status = status;
        return this;
    }

    public PropertyBuilder type(String type) {
        this.type = type;
        return this;
    }

    public PropertyBuilder price(Double price) {
        this.price = price;
        return this;
    }

    public PropertyBuilder address(String address) {
        this.address = address;
        return this;
    }

    public PropertyBuilder area(Double area) {
        this.area = area;
        return this;
    }

    public PropertyBuilder beds(Integer beds) {
        this.beds = beds;
        return this;
    }

    public PropertyBuilder baths(Integer baths) {
        this.baths = baths;
        return this;
    }

    public PropertyEntity build() {
        PropertyEntity property = new PropertyEntity();
        property.setId((long) Math.toIntExact(id));
        property.setTitle(tittle);
        property.setImageUrl(imageUrl);
        property.setStatus(status);
        property.setType(type);
//        property.setPrice(price);
        property.setAddress(address);
//        property.setConstructedArea(area);
//        property.setNumBedrooms(beds);
//        property.setNumBathrooms(baths);
        return property;
    }
}
