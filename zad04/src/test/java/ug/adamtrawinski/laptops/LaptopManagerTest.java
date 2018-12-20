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
import ug.adamtrawinski.laptops.service.LaptopManager;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class LaptopManagerTest {

    @Autowired
    LaptopManager lm;

    private final String NAME_1 = "YOGA 520";
    private final String NAME_2 = "250 G6";
    private final String NAME_3 = "Blade";
    private final boolean USED_1 = true;
    private final boolean USED_2 = false;
    private final Date RELEASE_DATE_1 = new GregorianCalendar(2018, Calendar.FEBRUARY, 11).getTime();
    private final Date RELEASE_DATE_2 = new GregorianCalendar(2017, Calendar.MARCH, 25).getTime();
    private final double PRICE_1 = 1500.23;
    private final double PRICE_2 = 976.00;


    @Before
    public void prepare() {
        lm.clearTable();
        Laptop lenovo = new Laptop(NAME_1, USED_1, RELEASE_DATE_1, PRICE_1);
        lm.addLaptop(lenovo);

        Laptop hp = new Laptop(NAME_2, USED_2, RELEASE_DATE_2, PRICE_2);
        lm.addLaptop(hp);
    }

    @After
    public void clean() {
        lm.clearTable();
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
}
