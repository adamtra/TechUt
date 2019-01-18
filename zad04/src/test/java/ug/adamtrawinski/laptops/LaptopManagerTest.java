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
import ug.adamtrawinski.laptops.domain.Processor;
import ug.adamtrawinski.laptops.domain.SerialCode;
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
    ProcessorManager pm;

    private final String NAME_1 = "YOGA 520";
    private final String NAME_2 = "250 G6";
    private final String NAME_3 = "Blade";
    private final boolean USED_1 = true;
    private final boolean USED_2 = false;
    private final Date RELEASE_DATE_1 = new GregorianCalendar(2018, Calendar.FEBRUARY, 11).getTime();
    private final Date RELEASE_DATE_2 = new GregorianCalendar(2017, Calendar.MARCH, 25).getTime();
    private final double PRICE_1 = 1500.23;
    private final double PRICE_2 = 976.00;

    private final String SERIAL_CODE_1 = "ZY89-2SDA";

    private final String MANUFACTURER_NAME_1 = "Lenovo";
    private final int MANUFACTURER_OPERATE_SINCE_1 = 1990;

    private final String PROCESSOR_NAME_1 = "i5 4690k";
    private final String PROCESSOR_NAME_2 = "i7 7700";


    @Before
    public void prepare() {
        lm.clearTable();
        scm.clearTable();
        mm.clearTable();

        Laptop lenovo = new Laptop(NAME_1, USED_1, RELEASE_DATE_1, PRICE_1);
        lm.addLaptop(lenovo);
        Laptop hp = new Laptop(NAME_2, USED_2, RELEASE_DATE_2, PRICE_2);
        lm.addLaptop(hp);

        SerialCode serialCode = new SerialCode(SERIAL_CODE_1);
        scm.addSerialCode(serialCode);
        Laptop laptop1 = lm.findLaptopById(2);
        laptop1.setSerialCode(serialCode);
        lm.updateLaptop(laptop1);


        Manufacturer manufacturer = new Manufacturer(MANUFACTURER_NAME_1, MANUFACTURER_OPERATE_SINCE_1);
        mm.addManufacturer(manufacturer);
        Laptop laptop2 = lm.findLaptopById(1);
        laptop2.setManufacturer(manufacturer);
        lm.updateLaptop(laptop2);

        Processor processor1 = new Processor(PROCESSOR_NAME_1);
        pm.addProcessor(processor1);
        Processor processor2 = new Processor(PROCESSOR_NAME_2);
        pm.addProcessor(processor2);
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
        Laptop retrieved = lm.findLaptopById(2);
        assertEquals(retrieved.getSerialCode().getCode(), SERIAL_CODE_1);
    }

    @Test
    public void assignManufacturer() {
        Laptop retrieved = lm.findLaptopById(1);
        assertEquals(retrieved.getManufacturer().getName(), MANUFACTURER_NAME_1);
    }
}
