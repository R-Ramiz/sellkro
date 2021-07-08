package com.example.sama.sellkro;

/**
 * Created by sama on 4/9/2021.
 */
public class post
{
    int id;
    String Title,Category,Details,Address,Price,joiningdate;


    public post(int id,String title, String category, String address, String price, String joiningdate)
    {
        this.id = id;
        this.Title = title;
        this.Category =  category;
        this.Address = address;
        this.Price = price;
        this.joiningdate = joiningdate;

    }

    public post(int id, String category,String title, String details, String address, String price, String joiningdate)
    {
        this.id = id;
        this.Title = title;
        this.Details = details;
        this.Address = address;
        this.Price = price;
        this.joiningdate = joiningdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getJoiningdate() {
        return joiningdate;
    }

    public void setJoiningdate(String joiningdate) {
        this.joiningdate = joiningdate;
    }
}
