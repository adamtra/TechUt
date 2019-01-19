package ug.adamtrawinski.laptops.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ug.adamtrawinski.laptops.domain.Owner;

import java.util.List;

@Component
@Transactional
public class OwnerMangerHibernateImpl implements OwnerManager {

	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public void addOwner(Owner owner) {
		sessionFactory.getCurrentSession().persist(owner);
	}

	@Override
	public Owner findOwnerById(long id) {
		return (Owner) sessionFactory.getCurrentSession().get(Owner.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Owner> getAllOwners() {
		return sessionFactory.getCurrentSession().createCriteria(Owner.class).list();
	}

	@Override
	public void deleteOwner(long id) {
		Owner owner = findOwnerById(id);
		sessionFactory.getCurrentSession().delete(owner);
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateOwner(Owner owner) {
		Owner old = findOwnerById(owner.getId());
		if(old != null) {
			sessionFactory.getCurrentSession().merge(owner);
		}
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void clearTable() {
		sessionFactory.getCurrentSession().createSQLQuery("TRUNCATE TABLE LAPTOP_OWNER").executeUpdate();
		sessionFactory.getCurrentSession().createSQLQuery("TRUNCATE TABLE OWNER RESTART IDENTITY").executeUpdate();
	}


}
