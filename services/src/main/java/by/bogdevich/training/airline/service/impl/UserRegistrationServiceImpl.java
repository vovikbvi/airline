package by.bogdevich.training.airline.service.impl;

import javax.inject.Inject;

import by.bogdevich.training.airline.dataaccess.UserDao;
import by.bogdevich.training.airline.datamodel.User;
import by.bogdevich.training.airline.service.UserRegistrationService;

public class UserRegistrationServiceImpl implements UserRegistrationService{
	
	@Inject
	UserDao userDao;

	@Override
	public void userRegistration(User user) {
		// TODO Auto-generated method stub
		
	}

	
}
