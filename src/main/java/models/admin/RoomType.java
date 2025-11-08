package models.admin;

import com.github.javafaker.Faker;

public class RoomType {
    private String title;
    private int price;
    private int adultCapacity;
    private int childrenCapacity;
    private String description;
    private static final Faker faker = new Faker();


    public RoomType(String title, int price, int adultCapacity, int childrenCapacity, String description) {
        this.title = title;
        this.price = price;
        this.adultCapacity = adultCapacity;
        this.childrenCapacity = childrenCapacity;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAdultCapacity() {
        return adultCapacity;
    }

    public void setAdultCapacity(int adultCapacity) {
        this.adultCapacity = adultCapacity;
    }

    public int getChildrenCapacity() {
        return childrenCapacity;
    }

    public void setChildrenCapacity(int childrenCapacity) {
        this.childrenCapacity = childrenCapacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
