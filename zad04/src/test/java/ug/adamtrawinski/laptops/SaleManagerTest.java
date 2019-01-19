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
import ug.adamtrawinski.laptops.domain.Sale;
import ug.adamtrawinski.laptops.service.SalesManager;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class SaleManagerTest {

    @Autowired
    SalesManager sm;

    private final String SALE_DESCRIPTION_1 = "Duża sprzedaż tego towaru";
    private final int SALE_SOLD_1 = 5;
    private final String SALE_DESCRIPTION_2 = "Jakiś klient";
    private final int SALE_SOLD_2 = 1;
    private final String SALE_DESCRIPTION_3 = "Istotny klient";


    @Before
    public void prepare() {
        sm.clearTable();
        Sale sale1 = new Sale(SALE_DESCRIPTION_1, SALE_SOLD_1);
        sm.addSale(sale1);
        Sale sale2 = new Sale(SALE_DESCRIPTION_2, SALE_SOLD_2);
        sm.addSale(sale2);
    }

    @After
    public void clean() {
        sm.clearTable();
    }

    @Test
    public void addSaleCheck() {
        Sale retrieved = sm.findSaleById(1);
        assertEquals(SALE_DESCRIPTION_1, retrieved.getDescription());

        Sale retrieved_2 = sm.findSaleById(2);
        assertEquals(SALE_DESCRIPTION_2, retrieved_2.getDescription());
    }


    @Test
    public void getSalesCheck() {
        List<Sale> sales = sm.getAllSales();
        assertEquals(2, sales.size());
    }

    @Test
    public void updateSaleCheck() {
        Sale sale = new Sale(SALE_DESCRIPTION_3, SALE_SOLD_2);
        sale.setId(2);
        sm.updateSale(sale);
        Sale retrieved = sm.findSaleById(2);
        assertEquals(SALE_DESCRIPTION_3, retrieved.getDescription());
    }

    @Test
    public void deleteSale() {
        sm.deleteSale(1);
        Sale retrieved = sm.findSaleById(1);
        assertNull(retrieved);
    }
}
