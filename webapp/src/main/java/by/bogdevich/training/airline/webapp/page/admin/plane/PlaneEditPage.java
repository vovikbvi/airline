package by.bogdevich.training.airline.webapp.page.admin.plane;

import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import by.bogdevich.training.airline.datamodel.ModelPlane;
import by.bogdevich.training.airline.datamodel.Plane;
import by.bogdevich.training.airline.service.ModelPlaneService;
import by.bogdevich.training.airline.service.PlaneService;
import by.bogdevich.training.airline.webapp.common.renderer.ModelPlaneChoiceRenderer;
import by.bogdevich.training.airline.webapp.page.AbstractPage;

@AuthorizeInstantiation(value = {"ADMIN", "OPERATOR"})
public class PlaneEditPage extends AbstractPage {

	@Inject
	private PlaneService planeService;
	
	@Inject
	private ModelPlaneService modelPlaneService;

	private Plane plane;

	public PlaneEditPage() {
		super();
	}

	public PlaneEditPage(Plane plane) {
		super();
		this.plane = plane;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		Form form = new Form("form", new CompoundPropertyModel<Plane>(plane));
		add(form);
  
    	TextField<String> bortNumberField = new TextField<>("bortNumber");
		bortNumberField.setRequired(true);
		form.add(bortNumberField);
		
		ArrayList<ModelPlane> listModelPlane= new ArrayList<ModelPlane>(modelPlaneService.getAll());
        DropDownChoice<ModelPlane> modelPlaneField = new DropDownChoice<ModelPlane>("modelPlane", listModelPlane, ModelPlaneChoiceRenderer.INSTANCE);
        modelPlaneField.setRequired(true);
        form.add(modelPlaneField);
 	
		
		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				
				if (plane.getId() == null) {
					planeService.insert(plane);
				} else {
					planeService.update(plane);
				}

				setResponsePage(new PlanePage());
			}
		});

		add(new FeedbackPanel("feedback"));

		
	}
}
