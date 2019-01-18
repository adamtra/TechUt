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
import ug.adamtrawinski.laptops.domain.Owner;
import ug.adamtrawinski.laptops.service.OwnerManager;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class OwnerManagerTest {

    @Autowired
    OwnerManager om;

    private final String OWNER_FIRST_NAME_1 = "Jan";
    private final String OWNER_LAST_NAME_1 = "Kowalski";
    private final String OWNER_FIRST_NAME_2 = "Piotr";
    private final String OWNER_LAST_NAME_2 = "Malinowski";
    private final String OWNER_FIRST_NAME_3 = "Krzysztof";


    @Before
    public void prepare() {
        om.clearTable();
        Owner owner1 = new Owner(OWNER_FIRST_NAME_1, OWNER_LAST_NAME_1);
        om.addOwner(owner1);
        Owner owner2 = new Owner(OWNER_FIRST_NAME_2, OWNER_LAST_NAME_2);
        om.addOwner(owner2);
    }

    @After
    public void clean() {
        om.clearTable();
    }

    @Test
    public void addOwnerCheck() {
        Owner retrieved = om.findOwnerById(1);
        assertEquals(OWNER_FIRST_NAME_1, retrieved.getFirstName());

        Owner retrieved_2 = om.findOwnerById(2);
        assertEquals(OWNER_FIRST_NAME_2, retrieved_2.getFirstName());
    }


    @Test
    public void getOwnersCheck() {
        List<Owner> owners = om.getAllOwners();
        assertEquals(2, owners.size());
    }

    @Test
    public void updateOwnerCheck() {
        Owner owner = new Owner(OWNER_FIRST_NAME_3, OWNER_LAST_NAME_1);
        owner.setId(2);
        om.updateOwner(owner);
        Owner retrieved = om.findOwnerById(2);
        assertEquals(OWNER_FIRST_NAME_3, retrieved.getFirstName());
    }

    @Test
    public void deleteOwner() {
        om.deleteOwner(1);
        Owner retrieved = om.findOwnerById(1);
        assertNull(retrieved);
    }
}
