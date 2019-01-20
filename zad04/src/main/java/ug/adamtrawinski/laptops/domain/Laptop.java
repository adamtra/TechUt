package ug.adamtrawinski.laptops.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "laptop.findByName", query = "SELECT l FROM Laptop l WHERE l.name = :name"),
        @NamedQuery(name = "laptop.findByNameLike", query = "SELECT l FROM Laptop l WHERE LOWER(l.name) LIKE LOWER(CONCAT('%', :name, '%'))"),
        @NamedQuery(name = "laptop.findNewerThan", query = "SELECT l FROM Laptop l WHERE l.releaseDate >= :releaseDate"),
        @NamedQuery(name = "laptop.findPriceBetween", query = "SELECT l FROM Laptop l WHERE l.price >= :min AND l.price <= :max"),
        @NamedQuery(name = "laptop.findBySerialCode", query = "SELECT l FROM Laptop l JOIN l.serialCode sc WHERE sc.code = :code"),
        @NamedQuery(name = "laptop.findByManufacturer", query = "SELECT l FROM Laptop l JOIN l.manufacturer m WHERE m.id = :manufacturer"),
        @NamedQuery(name = "laptop.findWhereSaleBiggerThan", query = "SELECT l FROM Laptop l JOIN l.sales s GROUP BY l.id HAVING SUM(s.sold) >= :sold")
})
public class Laptop {
    private long id;
    private String name;
    private boolean used;
    private Date releaseDate;
    private double price;

    private List<Owner> owners = new ArrayList<Owner>();
    private Manufacturer manufacturer;
    private SerialCode serialCode;
    private List<Sale> sales = new ArrayList<Sale>();

    @Override
    public String toString() {
        String state;
        String response = "";
        if(used) {
            state = "Użytany";
        }
        else {
            state = "Nowy";
        }

        response += "Name: " + name + "\n";
        response += "Used: " + state + "\n";
        response += "Release Date: " + releaseDate + "\n";
        response += "Price: " + price + "\n";

        if(owners.size() > 0) {
            response += "Owners: \n";
            for (Owner o: owners) {
                response += o + "\n";
            }
        }

        response += "Manufacturer: " + manufacturer + "\n";
        response += "Serial Code: " + serialCode + "\n";

        if(sales.size() > 0) {
            response += "Processors: \n";
            for (Sale p: sales) {
                response += p + "\n";
            }
        }

        return response;
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

    public Laptop(String name, boolean used, Date releaseDate, double price, List<Owner> owners, Manufacturer manufacturer, SerialCode serialCode, List<Sale> sales) {
        this.name = name;
        this.used = used;
        this.releaseDate = releaseDate;
        this.price = price;
        this.owners = owners;
        this.manufacturer = manufacturer;
        this.serialCode = serialCode;
        this.sales = sales;
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
    public List<Owner> getOwners() {
        return owners;
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
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
    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
}
