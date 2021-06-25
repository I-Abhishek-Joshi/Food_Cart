package com.example.foodcart;

public class food_item {
    String restaurantName, itemName, description;
    int price, imageId = -1, cost,  quantity, id;

    public food_item(int id, String restaurantName, String itemName, String description, int price, int imageId) {
        this.restaurantName = restaurantName;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.imageId = imageId;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public food_item(int id, String restaurantName, String itemName, String description, int price, int imageId, int quantity) {
        this.restaurantName = restaurantName;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.imageId = imageId;
        this.quantity = quantity;
        this.id = id;
    }
    public  food_item(){}

    @Override
    public String toString() {
        return "food_item{" +
                "restaurantName='" + restaurantName + '\'' +
                ", itemName='" + itemName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imageId=" + imageId +
                '}';
    }

    public food_item(int id, int imageId, String itemName, int price, int cost, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.imageId = imageId;
        this.cost = cost;
        this.quantity = quantity;
        this.id = id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
