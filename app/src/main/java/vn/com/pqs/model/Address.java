package vn.com.pqs.model;

import java.io.Serializable;

/**
 * Created by long on 03/12/2016.
 */
public class Address implements Serializable {
   String name,namena,ad,adna,price,phone;
    int id;

    public Address() {
    }

    public Address(String ad, String adna, int id, String name, String namena, String phone, String price) {
        this.ad = ad;
        this.adna = adna;
        this.id = id;
        this.name = name;
        this.namena = namena;
        this.phone = phone;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getAdna() {
        return adna;
    }

    public void setAdna(String adna) {
        this.adna = adna;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamena() {
        return namena;
    }

    public void setNamena(String namena) {
        this.namena = namena;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}