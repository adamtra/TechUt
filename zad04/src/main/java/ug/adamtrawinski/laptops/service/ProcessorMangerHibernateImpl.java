package ug.adamtrawinski.laptops.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ug.adamtrawinski.laptops.domain.Processor;

import java.util.List;

@Component
@Transactional
public class ProcessorMangerHibernateImpl implements ProcessorManager {

	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public void addProcessor(Processor processor) {
		sessionFactory.getCurrentSession().persist(processor);
	}

	@Override
	public Processor findProcessorById(long id) {
		return (Processor) sessionFactory.getCurrentSession().get(Processor.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Processor> getAllProcessors() {
		return sessionFactory.getCurrentSession().createCriteria(Processor.class).list();
	}

	@Override
	public void deleteProcessor(long id) {
		Processor processor = findProcessorById(id);
		sessionFactory.getCurrentSession().delete(processor);
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateProcessor(Processor processor) {
		Processor old = findProcessorById(processor.getId());
		if(old != null) {
			sessionFactory.getCurrentSession().merge(processor);
		}
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void clearTable() {
		sessionFactory.getCurrentSession().createSQLQuery("TRUNCATE TABLE PROCESSOR RESTART IDENTITY").executeUpdate();
	}


}
