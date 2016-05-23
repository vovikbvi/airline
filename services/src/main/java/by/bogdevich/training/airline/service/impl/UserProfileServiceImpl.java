package by.bogdevich.training.airline.service.impl;

import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import by.bogdevich.training.airline.dataaccess.UserProfileDao;
import by.bogdevich.training.airline.dataaccess.filtres.UserProfileFilter;
import by.bogdevich.training.airline.datamodel.UserProfile;
import by.bogdevich.training.airline.service.UserProfileService;
import by.bogdevich.training.airline.service.util.SendMail;

@Service
public class UserProfileServiceImpl implements UserProfileService {
	private static Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

	@Inject
	UserProfileDao userProfileDao;

	private boolean checkUserExist(String login) {
		if (userProfileDao.countLogin(login) == 0) {
			return false;
		}
		return true;
	}

	private void sendMessage(UserProfile userProfile) {
		String subject = "Registr LowCostAirline";
		String textMail = "You registr in LowCostAirline";
		String fromEmail = "LowCostAirlineTrening@gmail.com";
		String password = "lowcostairline";
		String toEmail = userProfile.getEmail();

		SendMail sender = new SendMail(fromEmail, password);
		sender.send(subject, textMail, fromEmail, toEmail);

	}

	private void AcceptRegistration(UserProfile userProfile) {
		// sendMessage(userProfile);
		userProfile.setCountOder(0);
		userProfile.setDateRegistr(new Date());
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
}
