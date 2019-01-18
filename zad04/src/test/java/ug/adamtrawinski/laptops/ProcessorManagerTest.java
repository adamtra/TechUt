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
import ug.adamtrawinski.laptops.domain.Processor;
import ug.adamtrawinski.laptops.service.ProcessorManager;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class ProcessorManagerTest {

    @Autowired
    ProcessorManager pm;

    private final String PROCESSOR_NAME_1 = "i5 4690k";
    private final String PROCESSOR_NAME_2 = "i7 7700";
    private final String PROCESSOR_NAME_3 = "i3 8750";


    @Before
    public void prepare() {
        pm.clearTable();
        Processor processor1 = new Processor(PROCESSOR_NAME_1);
        pm.addProcessor(processor1);
        Processor processor2 = new Processor(PROCESSOR_NAME_2);
        pm.addProcessor(processor2);
    }

    @After
    public void clean() {
        pm.clearTable();
    }

    @Test
    public void addProcessorCheck() {
        Processor retrieved = pm.findProcessorById(1);
        assertEquals(PROCESSOR_NAME_1, retrieved.getName());

        Processor retrieved_2 = pm.findProcessorById(2);
        assertEquals(PROCESSOR_NAME_2, retrieved_2.getName());
    }


    @Test
    public void getProcessorsCheck() {
        List<Processor> processors = pm.getAllProcessors();
        assertEquals(2, processors.size());
    }

    @Test
    public void updateProcessorCheck() {
        Processor processor = new Processor(PROCESSOR_NAME_3);
        processor.setId(2);
        pm.updateProcessor(processor);
        Processor retrieved = pm.findProcessorById(2);
        assertEquals(PROCESSOR_NAME_3, retrieved.getName());
    }

    @Test
    public void deleteProcessor() {
        pm.deleteProcessor(1);
        Processor retrieved = pm.findProcessorById(1);
        assertNull(retrieved);
    }
}
