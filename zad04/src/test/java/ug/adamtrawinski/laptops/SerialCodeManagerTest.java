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
import ug.adamtrawinski.laptops.domain.SerialCode;
import ug.adamtrawinski.laptops.service.SerialCodeManager;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class SerialCodeManagerTest {

    @Autowired
    SerialCodeManager scm;

    private final String CODE_1 = "QMH-34A";
    private final String CODE_2 = "MKY-WM9";
    private final String CODE_3 = "AAA-AAA";

    @Before
    public void prepare() {
        scm.clearTable();
        SerialCode serialCode1 = new SerialCode(CODE_1);
        scm.addSerialCode(serialCode1);

        SerialCode serialCode2 = new SerialCode(CODE_2);
        scm.addSerialCode(serialCode2);
    }

    @After
    public void clean() {
        scm.clearTable();
    }

    @Test
    public void addSerialCodeCheck() {
        SerialCode retrieved = scm.findSerialCodeById(1);
        assertEquals(CODE_1, retrieved.getCode());

        SerialCode retrieved_2 = scm.findSerialCodeById(2);
        assertEquals(CODE_2, retrieved_2.getCode());
    }


    @Test
    public void getSerialCodesCheck() {
        List<SerialCode> SerialCodes = scm.getAllSerialCodes();
        assertEquals(2, SerialCodes.size());
    }

    @Test
    public void updateSerialCodeCheck() {
        SerialCode SerialCode = new SerialCode(CODE_3);
        SerialCode.setId(2);
        scm.updateSerialCode(SerialCode);
        SerialCode retrieved = scm.findSerialCodeById(2);
        assertEquals(CODE_3, retrieved.getCode());
    }

    @Test
    public void deleteSerialCode() {
        scm.deleteSerialCode(1);
        SerialCode retrieved = scm.findSerialCodeById(1);
        assertNull(retrieved);
    }
}
