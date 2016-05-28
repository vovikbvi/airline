package by.bogdevich.training.airline.webapp.common;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.bogdevich.training.airline.datamodel.Country;

public class CountryChoiceRenderer extends ChoiceRenderer<Country>{
	
	public static final CountryChoiceRenderer INSTANCE = new CountryChoiceRenderer();
	
	   @Override
	    public Object getDisplayValue(Country object) {
	        return object.getName();
	    }

	    @Override
	    public String getIdValue(Country object, int index) {
	        return String.valueOf(object.getId());
	    }


}
