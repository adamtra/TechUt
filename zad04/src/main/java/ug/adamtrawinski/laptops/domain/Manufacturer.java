package ug.adamtrawinski.laptops.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Manufacturer {
    private long id;
    private String name;
    private int operateSince;

    @Override
    public String toString() {
        return name + " " + operateSince;
    }

    public Manufacturer() {

    }

    public Manufacturer(String name, int operateSince) {
        this.name = name;
        this.operateSince = operateSince;
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

    public int getOperateSince() {
        return operateSince;
    }

    public void setOperateSince(int operateSince) {
        this.operateSince = operateSince;
    }
}
