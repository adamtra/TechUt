package ug.adamtrawinski.projekt.util;


import ug.adamtrawinski.projekt.domain.Laptop;
import ug.adamtrawinski.projekt.service.LaptopService;
import ug.adamtrawinski.projekt.service.LaptopServiceJDBC;

import java.sql.SQLException;
import java.text.ParseException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Run {
    public static void main(String[] args) throws ParseException, SQLException {

        Date releaseDate = new Date((2018 - 1900), 1, 2);

        Laptop laptop1 = new Laptop("Lenovo", false, releaseDate, 123.33);
        Laptop laptop2 = new Laptop("HP", true, releaseDate, 333.33);

        LaptopService laptopService = new LaptopServiceJDBC();

        laptopService.deleteLaptops();

        laptopService.addLaptop(laptop1);
        laptopService.addLaptop(laptop2);

//        Laptop test = laptopService.getLaptopByName("HPhh");
//        System.out.println(test);

        List<Laptop> laptops = new ArrayList<>();
        laptops = laptopService.getAllLaptops();
        for(Laptop laptop : laptops) {
            System.out.println(laptop);
        }
    }
}
