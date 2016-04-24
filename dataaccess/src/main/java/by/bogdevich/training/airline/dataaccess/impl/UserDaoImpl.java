package by.bogdevich.training.airline.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.bogdevich.training.airline.dataaccess.UserDao;
import by.bogdevich.training.airline.datamodel.User;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<User, Long> implements UserDao {

	protected UserDaoImpl() {
		super(User.class);
	}

}
