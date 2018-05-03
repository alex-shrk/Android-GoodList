package ru.ssau.sanya.goodslist.Database.Model;

public class Good {
    private Long id;
    private String name;
    private String description;
    private String price;
    private String count;

    public Good(String name,String description, String price, String count) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.count = count;
    }

    public Good() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
