package by.bogdevich.training.airline.service;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.bogdevich.training.airline.dataaccess.UserProfileDao;
import by.bogdevich.training.airline.datamodel.UserProfile;
import by.bogdevich.training.airline.datamodel.UserRole;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class UserExistTest {

	@Inject
	UserProfileDao userProfileDao;
	
	@Inject
	private UserProfileService userProfileService;
	
	@Test
	public void userExsist(){
		int i = 29;
		UserProfile userProfile = new UserProfile();
		userProfile.setLogin("LoginExist");
		userProfile.setPassword("pas" + i);
		userProfile.setFirstName("FirstName" + i);
		userProfile.setLastName("LastName" + i);
		userProfile.setEmail("vovik@mail.ru");
		userProfile.setPassportNumber("abcdfe" + i);
		userProfile.setPhoneNumber("+375297121212" + i);
		userProfile.setVip(false);
		userProfile.setRole(UserRole.ADMIN);
		userProfile.setAceptRegistr(false);

		userProfileService.registration(userProfile);

				
		boolean userNotExist = (userProfileDao.countLogin(userProfile.getLogin())==0);
		
		Assert.assertFalse(userNotExist);
		
	}
	
}
