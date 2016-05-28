package by.bogdevich.training.airline.webapp.common;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.bogdevich.training.airline.datamodel.City;


public class CityChoiceRenderer extends ChoiceRenderer<City>{
	
	public static final CityChoiceRenderer INSTANCE = new CityChoiceRenderer();
	
	   @Override
	    public Object getDisplayValue(City object) {
	        return object.getName();
	    }

	    @Override
	    public String getIdValue(City object, int index) {
	        return String.valueOf(object.getId());
	    }


}
