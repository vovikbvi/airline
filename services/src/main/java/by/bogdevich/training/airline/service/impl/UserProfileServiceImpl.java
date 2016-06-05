package by.bogdevich.training.airline.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import by.bogdevich.training.airline.dataaccess.UserProfileDao;
import by.bogdevich.training.airline.dataaccess.filtres.UserProfileFilter;
import by.bogdevich.training.airline.datamodel.UserProfile;
import by.bogdevich.training.airline.datamodel.UserRole;
import by.bogdevich.training.airline.service.SemdMail;
import by.bogdevich.training.airline.service.UserProfileService;
import by.bogdevich.training.airline.service.util.SendMail;

@Service
public class UserProfileServiceImpl implements UserProfileService {
	private static Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

	@Inject
	private UserProfileDao userProfileDao;

	@Inject
	private SemdMail crunchifyEmailAPI;

	public boolean checkUserExist(String login) {
		if (userProfileDao.countUserLogin(login) == 0) {
			return false;
		}
		return true;
	}

	@Async
	private void sendMessage(UserProfile userProfile) {

		// Spring Bean file you specified in /src/main/resources folder
		// String crunchifyConfFile = "service-context.xml";
		// ConfigurableApplicationContext context = new
		// ClassPathXmlApplicationContext(crunchifyConfFile);

		String toAddr = "vovikbvi@mail.ru";
		String fromAddr = "LowCostAirlineTrening@gmail.com";
		String subject = "Registr LowCostAirline";
		String body = "You registr in LowCostAirline";
		crunchifyEmailAPI.crunchifyReadyToSendEmail(toAddr, fromAddr, subject, body);

	}

	private void AcceptRegistration(UserProfile userProfile) {
		sendMessage(userProfile);
		userProfile.setCountOder(0);
		userProfile.setVip(false);
		userProfile.setDateRegistr(new Date());
		userProfile.setRole(UserRole.PASSANGER);
		userProfile.setAceptRegistr(false);
		userProfileDao.insert(userProfile);
		LOGGER.info("Add user {}", userProfile);
	}

	public void registration(UserProfile userProfile) {

		if (checkUserExist(userProfile.getLogin())) {
			LOGGER.warn("User exist {}", userProfile.getLogin());
		} else {
			AcceptRegistration(userProfile);
		}

	}

	@Override
	public void update(UserProfile userProfile) {
		userProfileDao.update(userProfile);
		LOGGER.info("Update user profile {}", userProfile);
	}

	@Override
	public void delete(Long id) {
		UserProfile userProfile = userProfileDao.get(id);
		userProfileDao.delete(id);
		LOGGER.info("Delete user profile {}", userProfile);
	}

	@Override
	public UserProfile get(Long id) {
		return userProfileDao.get(id);
	}

	@Override
	public List<UserProfile> getAll() {
		return userProfileDao.getAll();
	}

	@Override
	public List<UserProfile> getRecordsSorted(UserProfileFilter filter) {
		return userProfileDao.getRecordsSorted(filter);
	}

	@Override
	public Long count(UserProfileFilter filter) {
		return userProfileDao.count(filter);
	}

	@Override
	public UserProfile getPermissions(String userName, String password) {
		return userProfileDao.findUser(userName, password);
	}

	@Override
	public Collection<? extends String> resolveRoles(Long id) {
		UserProfile userProfile = userProfileDao.get(id);
		return Collections.singletonList(userProfile.getRole().name());
	}

}
