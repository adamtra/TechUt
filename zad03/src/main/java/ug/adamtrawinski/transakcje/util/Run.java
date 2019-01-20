package ug.adamtrawinski.transakcje.util;


import ug.adamtrawinski.transakcje.domain.Laptop;
import ug.adamtrawinski.transakcje.service.LaptopService;
import ug.adamtrawinski.transakcje.service.LaptopServiceJDBC;

import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Run {
    public static void main(String[] args) throws SQLException {

        Laptop laptop1 = new Laptop("Lenovo", false, new Date((2018 - 1900), 1, 2), 123.33);
        Laptop laptop2 = new Laptop("HP", false, new Date((2018 - 1900), 2, 5), 333.33);

        LaptopService laptopService = new LaptopServiceJDBC();

        laptopService.deleteLaptops();

        laptopService.addLaptop(laptop1);
        laptopService.addLaptop(laptop2);

        List<Laptop> laptops = new ArrayList<>();

        Laptop laptop3 = new Laptop("Acer", true, new Date((2017 - 1900), 10, 2), 250.00);
        Laptop laptop4 = new Laptop("Dell", true, new Date((2015 - 1900), 2, 5), 1200.31);

        laptops.add(laptop3);
        laptops.add(laptop4);

        laptopService.addAllLaptops(laptops);


        List<Laptop> laptops2 = new ArrayList<>();
        Laptop laptop5 = new Laptop("Asus", true, new Date((2015 - 1900), 2, 5), 328.31);
        laptops2.add(laptop3);
        laptops2.add(laptop5);
        laptopService.addAllLaptops(laptops2);

        Laptop laptop6 = new Laptop("Apple", true, new Date((2018 - 1900), 5, 6), 1300.99);
        laptopService.addLaptop(laptop6);

        List<Laptop> all = laptopService.getAllLaptops();
        System.out.println("Wszystkie");
        for(Laptop laptop: all) {
            System.out.println(laptop);
        }


        List<Laptop> used = laptopService.getUsedLaptops();
        System.out.println("Używane");
        for(Laptop laptop: used) {
            System.out.println(laptop);
        }

        Laptop lenovo = laptopService.getLaptopByName("Lenovo");
        System.out.println("Laptop Lenovo");
        System.out.println(lenovo);


        List<Laptop> newer = laptopService.getLaptopsNewerThan(new Date((2017 - 1900), 1, 1));
        System.out.println("Nowsze niż: 2017-01-01");
        for(Laptop laptop: newer) {
            System.out.println(laptop);
        }

        List<Laptop> price = laptopService.getLaptopsPriceBetween(250, 330);
        System.out.println("Cena pomiędzy 250 i 330");
        for(Laptop laptop: price) {
            System.out.println(laptop);
        }

        List<Laptop> like = laptopService.getLaptopsNameLike("lE");
        System.out.println("Nazwa zawiera frazę 'lE'");
        for(Laptop laptop: like) {
            System.out.println(laptop);
        }

    }
}
