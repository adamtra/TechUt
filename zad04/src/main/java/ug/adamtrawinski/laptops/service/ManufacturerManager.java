package ug.adamtrawinski.laptops.service;

import ug.adamtrawinski.laptops.domain.Manufacturer;

import java.util.List;

public interface ManufacturerManager {
    void addManufacturer(Manufacturer manufacturer);
    Manufacturer findManufacturerById(long id);
    List<Manufacturer> getAllManufacturers();
    void deleteManufacturer(long id);
    void updateManufacturer(Manufacturer manufacturer);
    void clearTable();
}
