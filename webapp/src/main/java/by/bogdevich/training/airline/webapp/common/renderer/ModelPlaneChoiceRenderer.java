package by.bogdevich.training.airline.webapp.common.renderer;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.bogdevich.training.airline.datamodel.ModelPlane;

public class ModelPlaneChoiceRenderer extends ChoiceRenderer<ModelPlane> {

	public static final ModelPlaneChoiceRenderer INSTANCE = new ModelPlaneChoiceRenderer();

	@Override
	public Object getDisplayValue(ModelPlane object) {
		return object.getModel();
	}

	@Override
	public String getIdValue(ModelPlane object, int index) {
		return String.valueOf(object.getId());
	}

}
