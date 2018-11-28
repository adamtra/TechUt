package ug.adamtrawinski.laptops.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ug.adamtrawinski.laptops.domain.Laptop;

import java.util.List;

@Component
@Transactional
public class LaptopMangerHibernateImpl implements LaptopManager {

	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public boolean addLaptop(Laptop laptop) {
		return false;
	}

	@Override
	public Laptop findLaptopById(long id) {
		return null;
	}

	@Override
	public List<Laptop> getAllLaptops() {
		return null;
	}
}
