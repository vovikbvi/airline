package by.bogdevich.training.airline.webapp.common.renderer;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.bogdevich.training.airline.datamodel.UserProfile;


public class UserProfileChoiceRenderer extends ChoiceRenderer<UserProfile>{
	
	public static final UserProfileChoiceRenderer INSTANCE = new UserProfileChoiceRenderer();
	
	   @Override
	    public Object getDisplayValue(UserProfile object) {
	        return object.getLogin();
	    }

	    @Override
	    public String getIdValue(UserProfile object, int index) {
	        return String.valueOf(object.getId());
	    }


}
