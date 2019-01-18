package ug.adamtrawinski.transakcje.service;

import ug.adamtrawinski.transakcje.domain.Laptop;

import java.sql.Date;
import java.util.List;


public interface LaptopService {

    boolean addLaptop(Laptop laptop);
    boolean deleteLaptop(long id);
    boolean deleteLaptops();
    Laptop getLaptopByName(String name);
    List<Laptop> getAllLaptops();
    List<Laptop> getUsedLaptops();
    List<Laptop> getLaptopsNewerThan(Date date);
    List<Laptop> getLaptopsPriceBetween(double min, double max);
    List<Laptop> getLaptopsNameLike(String name);
    boolean addAllLaptops(List<Laptop> laptops);
}
