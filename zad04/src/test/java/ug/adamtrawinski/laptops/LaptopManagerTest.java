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
import ug.adamtrawinski.laptops.domain.Laptop;
import ug.adamtrawinski.laptops.domain.Manufacturer;
import ug.adamtrawinski.laptops.domain.SerialCode;
import ug.adamtrawinski.laptops.service.LaptopManager;
import ug.adamtrawinski.laptops.service.ManufacturerManager;
import ug.adamtrawinski.laptops.service.SerialCodeManager;

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

    private final String NAME_1 = "YOGA 520";
    private final String NAME_2 = "250 G6";
    private final String NAME_3 = "Blade";
    private final boolean USED_1 = true;
    private final boolean USED_2 = false;
    private final Date RELEASE_DATE_1 = new GregorianCalendar(2018, Calendar.FEBRUARY, 11).getTime();
    private final Date RELEASE_DATE_2 = new GregorianCalendar(2017, Calendar.MARCH, 25).getTime();
    private final double PRICE_1 = 1500.23;
    private final double PRICE_2 = 976.00;

    private final String SERIAL_CODE = "ZY89-2SDA";

    private final String MANUFACTURER_NAME = "Lenovo";
    private final int MANUFACTURER_OPERATE_SINCE = 1990;


    @Before
    public void prepare() {
        lm.clearTable();
        scm.clearTable();
        mm.clearTable();
        Laptop lenovo = new Laptop(NAME_1, USED_1, RELEASE_DATE_1, PRICE_1);
        lm.addLaptop(lenovo);

        Laptop hp = new Laptop(NAME_2, USED_2, RELEASE_DATE_2, PRICE_2);
        lm.addLaptop(hp);
    }

    @After
    public void clean() {
        lm.clearTable();
        scm.clearTable();
        mm.clearTable();
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
    public void getLaptopsBetweenPrice() {
        List<Laptop> laptops = lm.findLaptopsBetweenPrice(1000, 2000);
        assertEquals(1, laptops.size());
        assertEquals(NAME_1, laptops.get(0).getName());
    }

    @Test
    public void getLaptopBySerialCode() {
        SerialCode serialCode = new SerialCode(SERIAL_CODE);
        scm.addSerialCode(serialCode);
        Laptop retrieved = lm.findLaptopById(2);
        retrieved.setSerialCode(serialCode);
        lm.updateLaptop(retrieved);

        Laptop laptop = lm.findLaptopBySerialCode(SERIAL_CODE);
        assertEquals(SERIAL_CODE, laptop.getSerialCode().getCode());
    }

    @Test
    public void getLaptopsByManufacturer() {
        Manufacturer manufacturer = new Manufacturer(MANUFACTURER_NAME, MANUFACTURER_OPERATE_SINCE);
        mm.addManufacturer(manufacturer);
        Laptop retrieved = lm.findLaptopById(1);
        retrieved.setManufacturer(manufacturer);
        lm.updateLaptop(retrieved);

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
        SerialCode serialCode = new SerialCode(SERIAL_CODE);
        scm.addSerialCode(serialCode);
        Laptop retrieved = lm.findLaptopById(2);
        retrieved.setSerialCode(serialCode);
        lm.updateLaptop(retrieved);
        assertEquals(retrieved.getSerialCode().getCode(), SERIAL_CODE);
    }

    @Test
    public void assignManufacturer() {
        Manufacturer manufacturer = new Manufacturer(MANUFACTURER_NAME, MANUFACTURER_OPERATE_SINCE);
        mm.addManufacturer(manufacturer);
        Laptop retrieved = lm.findLaptopById(1);
        retrieved.setManufacturer(manufacturer);
        lm.updateLaptop(retrieved);
        assertEquals(retrieved.getManufacturer().getName(), MANUFACTURER_NAME);
    }
}
