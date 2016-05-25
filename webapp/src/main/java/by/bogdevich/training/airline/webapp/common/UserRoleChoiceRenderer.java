package by.bogdevich.training.airline.webapp.common;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.bogdevich.training.airline.datamodel.UserRole;

public class UserRoleChoiceRenderer extends ChoiceRenderer<UserRole> {
	
	public static final UserRoleChoiceRenderer INSTANCE = new UserRoleChoiceRenderer();

		
    @Override
    public Object getDisplayValue(UserRole object) {
        return object.name();
    }

    @Override
    public String getIdValue(UserRole object, int index) {
        return String.valueOf(object.ordinal());
    }

}
