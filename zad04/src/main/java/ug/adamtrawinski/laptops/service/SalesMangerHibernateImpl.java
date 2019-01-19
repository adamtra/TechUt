package ug.adamtrawinski.laptops.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ug.adamtrawinski.laptops.domain.Sale;

import java.util.List;

@Component
@Transactional
public class SalesMangerHibernateImpl implements SalesManager {

	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public void addSale(Sale sale) {
		sessionFactory.getCurrentSession().persist(sale);
	}

	@Override
	public Sale findSaleById(long id) {
		return (Sale) sessionFactory.getCurrentSession().get(Sale.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Sale> getAllSales() {
		return sessionFactory.getCurrentSession().createCriteria(Sale.class).list();
	}

	@Override
	public void deleteSale(long id) {
		Sale sale = findSaleById(id);
		sessionFactory.getCurrentSession().delete(sale);
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateSale(Sale sale) {
		Sale old = findSaleById(sale.getId());
		if(old != null) {
			sessionFactory.getCurrentSession().merge(sale);
		}
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void clearTable() {
		sessionFactory.getCurrentSession().createSQLQuery("TRUNCATE TABLE LAPTOP_SALE").executeUpdate();
		sessionFactory.getCurrentSession().createSQLQuery("TRUNCATE TABLE SALE RESTART IDENTITY").executeUpdate();
	}


}
