package ug.adamtrawinski.laptops.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ug.adamtrawinski.laptops.domain.SerialCode;

import java.util.List;

@Component
@Transactional
public class SerialCodeMangerHibernateImpl implements SerialCodeManager {

	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public void addSerialCode(SerialCode serialCode) {
		sessionFactory.getCurrentSession().persist(serialCode);
	}

	@Override
	public SerialCode findSerialCodeById(long id) {
		return (SerialCode) sessionFactory.getCurrentSession().get(SerialCode.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SerialCode> getAllSerialCodes() {
		return sessionFactory.getCurrentSession().createCriteria(SerialCode.class).list();
	}

	@Override
	public void deleteSerialCode(long id) {
		SerialCode serialCode = findSerialCodeById(id);
		sessionFactory.getCurrentSession().delete(serialCode);
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateSerialCode(SerialCode serialCode) {
		SerialCode old = findSerialCodeById(serialCode.getId());
		if(old != null) {
			sessionFactory.getCurrentSession().merge(serialCode);
		}
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void clearTable() {
		sessionFactory.getCurrentSession().createSQLQuery("TRUNCATE TABLE SERIALCODE RESTART IDENTITY").executeUpdate();
	}


}
