package ug.adamtrawinski.laptops.service;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ug.adamtrawinski.laptops.domain.Laptop;

import java.util.Date;
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
	public List<Laptop> findLaptopsByName(String name) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("laptop.findByName");
		query.setParameter("name", name);
		return query.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Laptop> findLaptopsNewerThan(Date releaseDate) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("laptop.findNewerThan");
		query.setParameter("releaseDate", releaseDate);
		return query.list();
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<Laptop> findLaptopsBetweenPrice(double min, double max) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("laptop.findPriceBetween");
		query.setParameter("min", min);
		query.setParameter("max", max);
		return query.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Laptop findLaptopBySerialCode(String code) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("laptop.findBySerialCode");
		query.setParameter("code", code);
		return (Laptop) query.list().get(0);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Laptop> findLaptopsByManufacturer(Long manufacturer) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("laptop.findByManufacturer");
		query.setParameter("manufacturer", manufacturer);
		return query.list();
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<Laptop> getAllLaptops() {
		return sessionFactory.getCurrentSession().createCriteria(Laptop.class).list();
	}

	@Override
	public void deleteLaptop(long id) {
		Laptop laptop = findLaptopById(id);
		sessionFactory.getCurrentSession().delete(laptop);
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateLaptop(Laptop laptop) {
		Laptop old = findLaptopById(laptop.getId());
		if(old != null) {
			sessionFactory.getCurrentSession().merge(laptop);
		}
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void clearTable() {
		sessionFactory.getCurrentSession().createSQLQuery("TRUNCATE TABLE LAPTOP_PROCESSOR").executeUpdate();
		sessionFactory.getCurrentSession().createSQLQuery("TRUNCATE TABLE LAPTOP_OWNER").executeUpdate();
		sessionFactory.getCurrentSession().createSQLQuery("TRUNCATE TABLE LAPTOP RESTART IDENTITY").executeUpdate();
	}


}
