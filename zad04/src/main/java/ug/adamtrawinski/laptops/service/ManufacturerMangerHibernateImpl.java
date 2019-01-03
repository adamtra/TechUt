package ug.adamtrawinski.laptops.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ug.adamtrawinski.laptops.domain.Manufacturer;

import java.util.List;

@Component
@Transactional
public class ManufacturerMangerHibernateImpl implements ManufacturerManager {

	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public void addManufacturer(Manufacturer manufacturer) {
		sessionFactory.getCurrentSession().persist(manufacturer);
	}

	@Override
	public Manufacturer findManufacturerById(long id) {
		return (Manufacturer) sessionFactory.getCurrentSession().get(Manufacturer.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Manufacturer> getAllManufacturers() {
		return sessionFactory.getCurrentSession().createCriteria(Manufacturer.class).list();
	}

	@Override
	public void deleteManufacturer(long id) {
		Manufacturer manufacturer = findManufacturerById(id);
		sessionFactory.getCurrentSession().delete(manufacturer);
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateManufacturer(Manufacturer manufacturer) {
		Manufacturer old = findManufacturerById(manufacturer.getId());
		if(old != null) {
			sessionFactory.getCurrentSession().merge(manufacturer);
		}
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void clearTable() {
		sessionFactory.getCurrentSession().createSQLQuery("TRUNCATE TABLE MANUFACTURER RESTART IDENTITY").executeUpdate();
	}


}
