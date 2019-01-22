package ug.adamtrawinski.transakcje;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ug.adamtrawinski.transakcje.domain.Laptop;
import ug.adamtrawinski.transakcje.service.LaptopService;
import ug.adamtrawinski.transakcje.service.LaptopServiceJDBC;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class LaptopServiceTest {
    LaptopService ls = new LaptopServiceJDBC();

    private final String NAME_1 = "Lenovo";
    private final boolean USED_1 = false;
    private final Date RELEASE_DATE_1 = new Date((2018 - 1900), 1, 2);
    private final double PRICE_1 = 123.33;

    private final String NAME_2 = "HP";
    private final boolean USED_2 = false;
    private final Date RELEASE_DATE_2 = new Date((2018 - 1900), 2, 5);
    private final double PRICE_2 = 333.33;

    private final String NAME_3 = "Acer";
    private final boolean USED_3 = true;
    private final Date RELEASE_DATE_3 = new Date((2017 - 1900), 10, 2);
    private final double PRICE_3 = 255.00;

    private final String NAME_4 = "Dell";
    private final boolean USED_4 = true;
    private final Date RELEASE_DATE_4 = new Date((2015 - 1900), 2, 5);
    private final double PRICE_4 = 1200.31;

    private final String NAME_5 = "Asus";
    private final boolean USED_5 = true;
    private final Date RELEASE_DATE_5 = new Date((2015 - 1900), 2, 5);
    private final double PRICE_5 = 328.31;

    private final String NAME_6 = "Apple";
    private final boolean USED_6 = true;
    private final Date RELEASE_DATE_6 = new Date((2018 - 1900), 5, 6);
    private final double PRICE_6 = 1300.99;

    @Before
    public void prepare() {
        ls.clearTable();
        Laptop laptop1 = new Laptop(NAME_1, USED_1, RELEASE_DATE_1, PRICE_1);
        Laptop laptop2 = new Laptop(NAME_2, USED_2, RELEASE_DATE_2, PRICE_2);
        ls.addLaptop(laptop1);
        ls.addLaptop(laptop2);

        List<Laptop> laptops = new ArrayList<>();

        Laptop laptop3 = new Laptop(NAME_3, USED_3, RELEASE_DATE_3, PRICE_3);
        Laptop laptop4 = new Laptop(NAME_4, USED_4, RELEASE_DATE_4, PRICE_4);

        laptops.add(laptop3);
        laptops.add(laptop4);

        ls.addAllLaptops(laptops);


        List<Laptop> laptops2 = new ArrayList<>();
        Laptop laptop5 = new Laptop(NAME_5, USED_5, RELEASE_DATE_5, PRICE_5);
        laptops2.add(laptop3);
        laptops2.add(laptop5);
        ls.addAllLaptops(laptops2);

        Laptop laptop6 = new Laptop(NAME_6, USED_6, RELEASE_DATE_6, PRICE_6);
        ls.addLaptop(laptop6);
    }

    @After
    public void clean() {
        ls.clearTable();
    }

    @Test
    public void addLaptop() {
        Laptop retrieved1 = ls.getLaptopById(0);
        Laptop retrieved2 = ls.getLaptopById(3);
        Laptop retrieved3 = ls.getLaptopById(5);
        assertEquals(NAME_1, retrieved1.getName());
        assertEquals(NAME_4, retrieved2.getName());
        assertEquals(NAME_6, retrieved3.getName());
    }

    @Test
    public void deleteLaptop() {
        ls.deleteLaptop(2);
        Laptop retrieved = ls.getLaptopById(2);
        assertNull(retrieved);
    }

    @Test
    public void deleteLaptops() {
        ls.deleteLaptops();
        List<Laptop> laptops = ls.getAllLaptops();
        assertEquals(0, laptops.size());
    }

    @Test
    public void getLaptopByName() {
        Laptop retrieved = ls.getLaptopByName(NAME_2);
        assertEquals(NAME_2, retrieved.getName());
    }

    @Test
    public void getAllLaptops() {
        List<Laptop> laptops = ls.getAllLaptops();
        assertEquals(5, laptops.size());
    }

    @Test
    public void getUsedLaptops() {
        List<Laptop> laptops = ls.getUsedLaptops();
        assertEquals(3, laptops.size());
    }

    @Test
    public void getLaptopsNewerThan() {
        List<Laptop> laptops = ls.getLaptopsNewerThan(new Date((2017 - 1900), 1, 1));
        assertEquals(4, laptops.size());
    }

    @Test
    public void getLaptopsPriceBetween() {
        List<Laptop> laptops = ls.getLaptopsPriceBetween(250, 340);
        assertEquals(2, laptops.size());
    }

    @Test
    public void getLaptopsNameLike() {
        List<Laptop> laptops = ls.getLaptopsNameLike("lE");
        assertEquals(2, laptops.size());
    }
}
