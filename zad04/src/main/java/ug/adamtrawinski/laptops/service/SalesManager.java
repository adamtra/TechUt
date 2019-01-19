package ug.adamtrawinski.laptops.service;

import ug.adamtrawinski.laptops.domain.Sale;

import java.util.List;

public interface SalesManager {
    void addSale(Sale sale);
    Sale findSaleById(long id);
    List<Sale> getAllSales();
    void deleteSale(long id);
    void updateSale(Sale sale);
    void clearTable();
}
