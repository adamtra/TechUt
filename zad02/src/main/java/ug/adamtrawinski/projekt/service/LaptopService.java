package ug.adamtrawinski.projekt.service;

import ug.adamtrawinski.projekt.domain.Laptop;

import java.util.List;


public interface LaptopService {

    boolean addLaptop(Laptop laptop);
    boolean deleteLaptop(long id);
    boolean deleteLaptops();
    Laptop getLaptopByName(String name);
    List<Laptop> getAllLaptops();
}
