package by.bogdevich.training.airline.dataaccess.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AbstractDaoImpl {

	@PersistenceContext
	private EntityManager entityManager;
}
