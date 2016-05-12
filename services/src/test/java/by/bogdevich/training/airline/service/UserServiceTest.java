package by.bogdevich.training.airline.service;


import java.util.List;
import javax.inject.Inject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import by.bogdevich.training.airline.dataaccess.UserProfileDao;
import by.bogdevich.training.airline.dataaccess.filtres.UserProfileFilter;
import by.bogdevich.training.airline.datamodel.UserProfile;
import by.bogdevich.training.airline.datamodel.UserRole;
import by.bogdevich.training.airline.datamodel.UserProfile_;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class UserServiceTest extends AbstractTest{

	@Inject
	private UserProfileService userProfileService;

	@Inject
	UserProfileDao userProfileDao;

	@Test
	public void testAddUserProfile() {

		Assert.assertNotNull(userProfileAdd());

	}

	@Test
	public void testUpdateUserProfile() {
		String updatedField = "new field";
		UserProfile userProfile = userProfileAdd();
		userProfile.setLastName(updatedField);
		userProfileService.update(userProfile);

		Assert.assertEquals(updatedField, userProfileService.get(userProfile.getId()).getLastName());
	}

	@Test
	public void testDeletUserProfile() {
		UserProfile userProfile = userProfileAdd();
		userProfileService.delete(userProfile.getId());

		Assert.assertNull(userProfileService.get(userProfile.getId()));
	}


	@Test
	public void testSearch() {
		
		  List<UserProfile> all = userProfileService.getAll(); for (UserProfile
		userProfile : all) { userProfileService.delete(userProfile.getId());
		 }
		 
		for (int i = 0; i < 10; i++) {
			UserProfile userProfile = new UserProfile();
			userProfile.setLogin("login" + ((int) Math.random() * 1000) + i);
			userProfile.setPassword("pas");
			userProfile.setFirstName("FirstName");
			userProfile.setLastName("LastName" + i);
			userProfile.setEmail("vovik@mail.ru");
			userProfile.setPassportNumber("abcdfe" + i);
			userProfile.setPhoneNumber("+375297121212" + i);
			userProfile.setCountOder(1);
			userProfile.setVip(false);
			userProfile.setRole(UserRole.ADMIN);
			userProfile.setAceptRegistr(false);

			userProfileService.registration(userProfile);
		}

		UserProfileFilter filter = new UserProfileFilter();
		List<UserProfile> result = userProfileService.getRecordsSorted(filter);
		// Assert.assertEquals(10, result.size());

		// test paging
		filter.setSetFetchTickets(true);
		int limit = 5;
		filter.setLimit(limit);
		filter.setOffset(0);
		result = userProfileService.getRecordsSorted(filter);
		Assert.assertEquals(limit, result.size());

		// test sort
		filter.setLimit(null);
		filter.setOffset(null);
		filter.setSortOrder(true);
		filter.setSortProperty(UserProfile_.firstName);
		result = userProfileService.getRecordsSorted(filter);
		// Assert.assertEquals(10, result.size());
	}

	@Test
	public void testFiend() {
		for (int i = 0; i < 10; i++) {
			UserProfile userProfile = new UserProfile();
			userProfile.setLogin("login" + i);
			userProfile.setPassword("pas");
			userProfile.setFirstName("FirstName");
			userProfile.setLastName("LastName");
			userProfile.setEmail("vovik@mail.ru");
			userProfile.setPassportNumber("abcdfe");
			userProfile.setPhoneNumber("+375297121212");
			userProfile.setCountOder(0);
			userProfile.setVip(false);
			userProfile.setRole(UserRole.ADMIN);
			userProfile.setAceptRegistr(false);

			userProfileService.registration(userProfile);
		}

		UserProfileFilter filter = new UserProfileFilter();
		List<UserProfile> result = userProfileService.getRecordsSorted(filter);

		
		 filter.setFirstName("FirstName"); result =
		 userProfileService.getRecordsSorted(filter); Assert.assertEquals(10,
		 result.size());
		 
	 List<UserProfile> all = userProfileService.getAll();
		 for (UserProfile userProfile : all){
		 userProfileService.delete(userProfile.getId());
		
	}

	}
	@Test
	public void userExsist() {

		UserProfile userProfile = new UserProfile();
		userProfile.setLogin("LoginExist");
		userProfile.setPassword("pas");
		userProfile.setFirstName("FirstName");
		userProfile.setLastName("LastName");
		userProfile.setEmail("vovik@mail.ru");
		userProfile.setPassportNumber("abcdfe");
		userProfile.setPhoneNumber("+375297121212");
		userProfile.setVip(false);
		userProfile.setRole(UserRole.ADMIN);
		userProfile.setAceptRegistr(false);

		userProfileService.registration(userProfile);

		boolean userNotExist = (userProfileDao.countLogin(userProfile.getLogin()) == 0);

		Assert.assertFalse(userNotExist);

	}

	@Before
	public void dellAll(){
		deletAllData();
	}
}
