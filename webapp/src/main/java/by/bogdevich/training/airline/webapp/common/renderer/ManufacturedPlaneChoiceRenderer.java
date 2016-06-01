package by.bogdevich.training.airline.webapp.common.renderer;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.bogdevich.training.airline.datamodel.ManufacturedPlane;


public class ManufacturedPlaneChoiceRenderer extends ChoiceRenderer<ManufacturedPlane>{
	
	public static final ManufacturedPlaneChoiceRenderer INSTANCE = new ManufacturedPlaneChoiceRenderer();
	
	   @Override
	    public Object getDisplayValue(ManufacturedPlane object) {
	        return object.getName();
	    }

	    @Override
	    public String getIdValue(ManufacturedPlane object, int index) {
	        return String.valueOf(object.getId());
	    }

}
