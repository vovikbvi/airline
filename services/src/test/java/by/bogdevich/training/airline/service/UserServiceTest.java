package by.bogdevich.training.airline.service;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.bogdevich.training.airline.dataaccess.filtres.UserProfileFilter;
import by.bogdevich.training.airline.datamodel.UserProfile;
import by.bogdevich.training.airline.datamodel.UserRole;
import by.bogdevich.training.airline.datamodel.UserProfile_;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class UserServiceTest {

	@Inject
	private UserProfileService userProfileService;

	// add user
	@Test
	public void testUser() {
		// clean all data from user
		List<UserProfile> all = userProfileService.getAll();
		for (UserProfile userProfile : all) {
			userProfileService.delete(userProfile.getId());
		}


		int i = 20;
		UserProfile userProfile = new UserProfile();
		userProfile.setLogin("login" + i);
		userProfile.setPassword("pas" + i);
		userProfile.setFirstName("FirstName" + i);
		userProfile.setLastName("LastName" + i);
		userProfile.setEmail("vovik@mail.ru");
		userProfile.setPassportNumber("abcdfe" + i);
		userProfile.setPhoneNumber("+375297121212" + i);
		userProfile.setCountOder(1);
		userProfile.setVip(false);
		userProfile.setRole(UserRole.ADMIN);
		userProfile.setAceptRegistr(false);

		userProfileService.registration(userProfile);

		UserProfile userProfileAdd = userProfileService.get(userProfile.getId());

		Assert.assertNotNull(userProfileAdd);

		//test del
		userProfileService.delete(userProfileAdd.getId());
		Assert.assertNull(userProfileService.get(userProfile.getId()));
	}

	  @Test
	    public void testSearch() {
		  
			for(int i =0; i<10; i++){
			UserProfile userProfile = new UserProfile();
			userProfile.setLogin("login" + i);
			userProfile.setPassword("pas" + Math.random());
			userProfile.setFirstName("FirstName" + Math.random());
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
	        List<UserProfile> result = userProfileService.find(filter);
	        Assert.assertEquals(10, result.size());
	        
	        // test paging
	        filter.setFetchCredentials(true);
	        int limit = 5;
	        filter.setLimit(limit);
	        filter.setOffset(0);
	        result = userProfileService.find(filter);
	        Assert.assertEquals(limit, result.size());
            
	   //     for (UserProfile userProfile : result) {
	   //     System.out.println("++++++++++++++++++++"+userProfile); 
	   //     }
	        // test sort

	        filter.setLimit(null);
	        filter.setOffset(null);
	        filter.setSortOrder(true);
	        filter.setSortProperty(UserProfile_.firstName);
	        result = userProfileService.find(filter);
 	        Assert.assertEquals(10, result.size());

	    }
	
}
