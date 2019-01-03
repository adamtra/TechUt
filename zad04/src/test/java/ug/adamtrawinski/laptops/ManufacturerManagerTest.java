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
import ug.adamtrawinski.laptops.domain.Manufacturer;
import ug.adamtrawinski.laptops.service.ManufacturerManager;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class ManufacturerManagerTest {

    @Autowired
    ManufacturerManager mm;

    private final String NAME_1 = "Lenovo";
    private final String NAME_2 = "HP";
    private final String NAME_3 = "Razer";
    private final int OPERATE_SINCE = 1990;
    private final int OPERATE_SINCE_2 = 2010;


    @Before
    public void prepare() {
        mm.clearTable();
        Manufacturer lenovo = new Manufacturer(NAME_1, OPERATE_SINCE);
        mm.addManufacturer(lenovo);

        Manufacturer hp = new Manufacturer(NAME_2, OPERATE_SINCE_2);
        mm.addManufacturer(hp);
    }

    @After
    public void clean() {
        mm.clearTable();
    }

    @Test
    public void addManufacturerCheck() {
        Manufacturer retrieved = mm.findManufacturerById(1);
        assertEquals(NAME_1, retrieved.getName());

        Manufacturer retrieved_2 = mm.findManufacturerById(2);
        assertEquals(NAME_2, retrieved_2.getName());
    }


    @Test
    public void getManufacturersCheck() {
        List<Manufacturer> manufacturers = mm.getAllManufacturers();
        assertEquals(2, manufacturers.size());
    }

    @Test
    public void updateManufacturerCheck() {
        Manufacturer manufacturer = new Manufacturer(NAME_3, OPERATE_SINCE_2);
        manufacturer.setId(2);
        mm.updateManufacturer(manufacturer);
        Manufacturer retrieved = mm.findManufacturerById(2);
        assertEquals(NAME_3, retrieved.getName());
    }

    @Test
    public void deleteManufacturer() {
        mm.deleteManufacturer(1);
        Manufacturer retrieved = mm.findManufacturerById(1);
        assertNull(retrieved);
    }
}
