package by.bogdevich.training.airline.webapp.page.admin.plane;

import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.ModelPlane;
import by.bogdevich.training.airline.datamodel.Plane;
import by.bogdevich.training.airline.datamodel.Ticket;
import by.bogdevich.training.airline.datamodel.TicketClass;
import by.bogdevich.training.airline.datamodel.TicketTupe;
import by.bogdevich.training.airline.datamodel.UserProfile;
import by.bogdevich.training.airline.service.ModelPlaneService;
import by.bogdevich.training.airline.service.PlaneService;
import by.bogdevich.training.airline.service.PriceService;
import by.bogdevich.training.airline.service.UserProfileService;
import by.bogdevich.training.airline.webapp.common.FlightChoiceRenderer;
import by.bogdevich.training.airline.webapp.common.ModelPlaneChoiceRenderer;
import by.bogdevich.training.airline.webapp.common.TicketClassChoiceRenderer;
import by.bogdevich.training.airline.webapp.common.TicketTupeChoiceRenderer;
import by.bogdevich.training.airline.webapp.common.UserProfileChoiceRenderer;
import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.admin.ticket.TicketsPage;

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
