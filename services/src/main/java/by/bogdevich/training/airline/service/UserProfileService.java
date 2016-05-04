package by.bogdevich.training.airline.service;

import java.util.List;

import javax.transaction.Transactional;

import by.bogdevich.training.airline.dataaccess.filtres.AbstractFilter;
import by.bogdevich.training.airline.dataaccess.filtres.UserProfileFilter;
import by.bogdevich.training.airline.datamodel.UserProfile;

public interface UserProfileService {

	@Transactional
	void registration(UserProfile userProfile);

	@Transactional
	void update(UserProfile userProfile);

	@Transactional
	void delete(Long id);

	public UserProfile get(Long id);

	public List<UserProfile> getAll();
	
	public List<UserProfile> find(UserProfileFilter filter);

}