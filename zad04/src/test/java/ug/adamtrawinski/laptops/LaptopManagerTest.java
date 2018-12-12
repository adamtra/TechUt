package ug.adamtrawinski.laptops;

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

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class LaptopManagerTest {

    @Autowired
    LaptopManager lm;

    @Test
    public void addLaptopCheck() {
        Date date = new GregorianCalendar(2018, Calendar.FEBRUARY, 11).getTime();
        Laptop lenovo = new Laptop("Lenovo", true, date, 1500.23);
        lm.addLaptop(lenovo);

        Laptop retrieved = lm.findLaptopById(1);

        assertEquals(lenovo.getName(), retrieved.getName());
    }


}
