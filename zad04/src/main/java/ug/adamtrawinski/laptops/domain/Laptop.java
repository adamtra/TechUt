package ug.adamtrawinski.laptops.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Laptop {
    private long id;
    private String name;
    private boolean used;
    private Date releaseDate;
    private double price;

    private List<Owner> owner;
    private Manufacturer manufacturer;
    private SerialCode serialCode;
    private List<Processor> processor;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    @ManyToMany
    public List<Owner> getOwner() {
        return owner;
    }

    public void setOwner(List<Owner> owner) {
        this.owner = owner;
    }

    @ManyToOne
    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @OneToOne
    public SerialCode getSerialCode() {
        return serialCode;
    }

    public void setSerialCode(SerialCode serialCode) {
        this.serialCode = serialCode;
    }

    @OneToMany
    public List<Processor> getProcessor() {
        return processor;
    }

    public void setProcessor(List<Processor> processor) {
        this.processor = processor;
    }
}
