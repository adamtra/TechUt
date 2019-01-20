package ug.adamtrawinski.laptops;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ug.adamtrawinski.laptops.domain.*;
import ug.adamtrawinski.laptops.service.*;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class LaptopManagerTest {

    @Autowired
    LaptopManager lm;
    @Autowired
    SerialCodeManager scm;
    @Autowired
    ManufacturerManager mm;
    @Autowired
    OwnerManager om;
    @Autowired
    SalesManager sm;

    private final String NAME_1 = "YOGA 525";
    private final String NAME_2 = "250 G6";
    private final String NAME_3 = "Blade";
    private final boolean USED_1 = true;
    private final boolean USED_2 = false;
    private final Date RELEASE_DATE_1 = new GregorianCalendar(2018, Calendar.FEBRUARY, 11).getTime();
    private final Date RELEASE_DATE_2 = new GregorianCalendar(2017, Calendar.MARCH, 25).getTime();
    private final double PRICE_1 = 1500.23;
    private final double PRICE_2 = 976.00;

    private final String SERIAL_CODE_1 = "QMH-34A";
    private final String SERIAL_CODE_2 = "MKY-WM9";

    private final String MANUFACTURER_NAME_1 = "Lenovo";
    private final String MANUFACTURER_NAME_2 = "HP";
    private final int MANUFACTURER_OPERATE_SINCE_1 = 1990;
    private final int MANUFACTURER_OPERATE_SINCE_2 = 2010;

    private final String SALE_DESCRIPTION_1 = "Duża sprzedaż tego towaru";
    private final int SALE_SOLD_1 = 5;
    private final String SALE_DESCRIPTION_2 = "Jakiś klient";
    private final int SALE_SOLD_2 = 1;

    private final String OWNER_FIRST_NAME_1 = "Jan";
    private final String OWNER_LAST_NAME_1 = "Kowalski";
    private final String OWNER_FIRST_NAME_2 = "Piotr";
    private final String OWNER_LAST_NAME_2 = "Malinowski";


    @Before
    public void prepare() {
        lm.clearTable();
        scm.clearTable();
        mm.clearTable();
        om.clearTable();
        sm.clearTable();

        Laptop lenovo = new Laptop(NAME_1, USED_1, RELEASE_DATE_1, PRICE_1);
        lm.addLaptop(lenovo);
        Laptop hp = new Laptop(NAME_2, USED_2, RELEASE_DATE_2, PRICE_2);
        lm.addLaptop(hp);

        SerialCode serialCode1 = new SerialCode(SERIAL_CODE_1);
        scm.addSerialCode(serialCode1);
        SerialCode serialCode2 = new SerialCode(SERIAL_CODE_2);
        scm.addSerialCode(serialCode2);


        Manufacturer manufacturer1 = new Manufacturer(MANUFACTURER_NAME_1, MANUFACTURER_OPERATE_SINCE_1);
        mm.addManufacturer(manufacturer1);
        Manufacturer manufacturer2 = new Manufacturer(MANUFACTURER_NAME_2, MANUFACTURER_OPERATE_SINCE_2);
        mm.addManufacturer(manufacturer2);


        Sale sale1 = new Sale(SALE_DESCRIPTION_1, SALE_SOLD_1);
        sm.addSale(sale1);
        Sale sale2 = new Sale(SALE_DESCRIPTION_2, SALE_SOLD_2);
        sm.addSale(sale2);

        Owner owner1 = new Owner(OWNER_FIRST_NAME_1, OWNER_LAST_NAME_1);
        om.addOwner(owner1);
        Owner owner2 = new Owner(OWNER_FIRST_NAME_2, OWNER_LAST_NAME_2);
        om.addOwner(owner2);


        Laptop laptop1 = lm.findLaptopById(1);
        laptop1.setManufacturer(manufacturer1);
        laptop1.setSerialCode(serialCode2);
        laptop1.getOwners().add(owner2);
        lm.updateLaptop(laptop1);



        Laptop laptop2 = lm.findLaptopById(2);
        laptop2.setSerialCode(serialCode1);
        laptop2.getSales().add(sale1);
        laptop2.getSales().add(sale2);
        laptop2.setManufacturer(manufacturer2);
        laptop2.getOwners().add(owner1);
        laptop2.getOwners().add(owner2);
        lm.updateLaptop(laptop2);

    }

    @After
    public void clean() {
        lm.clearTable();
        scm.clearTable();
        mm.clearTable();
        om.clearTable();
        sm.clearTable();
    }

    @Test
    public void addLaptopCheck() {
        Laptop retrieved = lm.findLaptopById(1);
        assertEquals(NAME_1, retrieved.getName());

        Laptop retrieved_2 = lm.findLaptopById(2);
        assertEquals(NAME_2, retrieved_2.getName());
    }


    @Test
    public void getLaptopsCheck() {
        List<Laptop> laptops = lm.getAllLaptops();
        assertEquals(2, laptops.size());
    }

    @Test
    public void getLaptopsByName() {
        List<Laptop> laptops = lm.findLaptopsByName(NAME_2);
        assertEquals(1, laptops.size());
        assertEquals(NAME_2, laptops.get(0).getName());
    }

    @Test
    public void getLaptopsNameLike() {
        List<Laptop> laptops = lm.findLaptopsNameLike("25");
        assertEquals(2, laptops.size());
        assertEquals(NAME_1, laptops.get(0).getName());
        assertEquals(NAME_2, laptops.get(1).getName());
    }


    @Test
    public void getLaptopsBetweenPrice() {
        List<Laptop> laptops = lm.findLaptopsBetweenPrice(1000, 2000);
        assertEquals(1, laptops.size());
        assertEquals(NAME_1, laptops.get(0).getName());
    }

    @Test
    public void getLaptopsNewerThan() {
        List<Laptop> laptops = lm.findLaptopsNewerThan(new GregorianCalendar(2017, Calendar.APRIL, 25).getTime());
        assertEquals(1, laptops.size());
        assertEquals(NAME_1, laptops.get(0).getName());
    }

    @Test
    public void getLaptopBySerialCode() {
        Laptop laptop = lm.findLaptopBySerialCode(SERIAL_CODE_1);
        assertEquals(SERIAL_CODE_1, laptop.getSerialCode().getCode());
    }

    @Test
    public void getLaptopsByManufacturer() {
        List<Laptop> laptops = lm.findLaptopsByManufacturer(1L);
        assertEquals(1, laptops.size());
    }

    @Test
    public void updateLaptopCheck() {
        Laptop laptop = new Laptop(2, NAME_3, USED_2, RELEASE_DATE_2, PRICE_2);
        lm.updateLaptop(laptop);
        Laptop retrieved = lm.findLaptopById(2);
        assertEquals(NAME_3, retrieved.getName());
    }

    @Test
    public void deleteLaptop() {
        lm.deleteLaptop(1);
        Laptop retrieved = lm.findLaptopById(1);
        assertNull(retrieved);
    }

    @Test
    public void assignSerialCode() {
        Laptop retrieved1 = lm.findLaptopById(1);
        Laptop retrieved2 = lm.findLaptopById(2);
        assertEquals(SERIAL_CODE_2, retrieved1.getSerialCode().getCode());
        assertEquals(SERIAL_CODE_1, retrieved2.getSerialCode().getCode());
    }

    @Test
    public void assignManufacturer() {
        Laptop retrieved1 = lm.findLaptopById(1);
        Laptop retrieved2 = lm.findLaptopById(2);
        assertEquals(MANUFACTURER_NAME_1, retrieved1.getManufacturer().getName());
        assertEquals(MANUFACTURER_NAME_2, retrieved2.getManufacturer().getName());
    }

    @Test
    public void assignSales() {
        Laptop retrieved1 = lm.findLaptopById(1);
        Laptop retrieved2 = lm.findLaptopById(2);
        assertEquals(0, retrieved1.getSales().size());
        assertEquals(2, retrieved2.getSales().size());
    }

    @Test
    public void assignOwners() {
        Laptop retrieved1 = lm.findLaptopById(1);
        Laptop retrieved2 = lm.findLaptopById(2);
        assertEquals(1, retrieved1.getOwners().size());
        assertEquals(2, retrieved2.getOwners().size());
    }
}
