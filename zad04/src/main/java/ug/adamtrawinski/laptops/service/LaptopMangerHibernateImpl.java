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
	public void addLaptop(Laptop laptop) {
		sessionFactory.getCurrentSession().persist(laptop);
	}

	@Override
	public Laptop findLaptopById(long id) {
		return (Laptop) sessionFactory.getCurrentSession().get(Laptop.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Laptop> getAllLaptops() {
		return sessionFactory.getCurrentSession().createCriteria(Laptop.class).list();
	}
}
