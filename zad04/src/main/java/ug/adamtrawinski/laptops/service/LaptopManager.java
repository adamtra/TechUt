package ug.adamtrawinski.laptops.service;

import ug.adamtrawinski.laptops.domain.Laptop;

import java.util.List;

public interface LaptopManager {
    void addLaptop(Laptop laptop);
    Laptop findLaptopById(long id);
    List<Laptop> findLaptopsByName(String name);
    List<Laptop> findLaptopsBetweenPrice(double min, double max);
    Laptop findLaptopBySerialCode(String code);
    List<Laptop> findLaptopsByManufacturer(Long manufacturer);
    List<Laptop> getAllLaptops();
    void deleteLaptop(long id);
    void updateLaptop(Laptop laptop);
    void clearTable();
}
