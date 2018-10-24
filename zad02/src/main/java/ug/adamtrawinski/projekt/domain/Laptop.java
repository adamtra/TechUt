package ug.adamtrawinski.projekt.domain;

import java.sql.Date;

public class Laptop {
    private long id;
    private String name;
    private boolean used;
    private Date releaseDate;
    private double price;

    @Override
    public String toString() {
        String state;
        if(used) {
            state = "Użytany";
        }
        else {
            state = "Nowy";
        }
        return "Laptop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", used=" + state +
                ", releaseDate=" + releaseDate +
                ", price=" + price +
                '}';
    }

    public Laptop(long id, String name, boolean used, Date releaseDate, double price) {
        this.id = id;
        this.name = name;
        this.used = used;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public Laptop(String name, boolean used, Date releaseDate, double price) {
        this.name = name;
        this.used = used;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public Laptop() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
