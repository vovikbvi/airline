package by.bogdevich.training.airline.webapp.common.renderer;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.Plane;


public class PlaneChoiceRenderer extends ChoiceRenderer<Plane>{
	
	public static final PlaneChoiceRenderer INSTANCE = new PlaneChoiceRenderer();
	
	   @Override
	    public Object getDisplayValue(Plane object) {
	        return object.getBortNumber();
	    }

	    @Override
	    public String getIdValue(Plane object, int index) {
	        return String.valueOf(object.getId());
	    }


}
