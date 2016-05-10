package by.bogdevich.training.airline.dataaccess;

import java.util.List;

import by.bogdevich.training.airline.dataaccess.filtres.AbstractFilter;
import by.bogdevich.training.airline.dataaccess.filtres.UserProfileFilter;
import by.bogdevich.training.airline.datamodel.UserProfile;

public interface UserProfileDao extends AbstractDao<UserProfile, Long> {

	public Long countLogin(String login);

	public List<UserProfile> getRecordsSorted(UserProfileFilter filter);
}
