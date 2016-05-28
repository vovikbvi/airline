package by.bogdevich.training.airline.webapp.page.admin.modelplane;

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

import by.bogdevich.training.airline.datamodel.ClassWeight;
import by.bogdevich.training.airline.datamodel.ManufacturedPlane;
import by.bogdevich.training.airline.datamodel.ModelPlane;
import by.bogdevich.training.airline.service.ManufacturedPlainService;
import by.bogdevich.training.airline.service.ModelPlaneService;
import by.bogdevich.training.airline.webapp.common.ClassWeightChoiceRenderer;
import by.bogdevich.training.airline.webapp.common.ManufacturedPlaneChoiceRenderer;
import by.bogdevich.training.airline.webapp.page.AbstractPage;


@AuthorizeInstantiation(value = {"ADMIN", "OPERATOR"})
public class ModelPlaneEditPage extends AbstractPage {
	
	@Inject
	private ModelPlaneService modelPlaneService;
	
	@Inject ManufacturedPlainService manufacturedPlainService;
	
	private ModelPlane modelPlane;
	
    public ModelPlaneEditPage() {
        super();
    }

	public ModelPlaneEditPage(ModelPlane modelPlane) {
		super();
		this.modelPlane = modelPlane;
	}

    @Override
    protected void onInitialize() {
    	super.onInitialize();
    	
		Form form = new Form("form", new CompoundPropertyModel<ModelPlane>(modelPlane));
		add(form);
  
		ArrayList<ManufacturedPlane> listManufactured= new ArrayList<ManufacturedPlane>(manufacturedPlainService.getAll());
        DropDownChoice<ManufacturedPlane> ManufacturedField = new DropDownChoice<ManufacturedPlane>("manufacturedPlane", listManufactured, ManufacturedPlaneChoiceRenderer.INSTANCE);
        ManufacturedField.setRequired(true);
        form.add(ManufacturedField);
		
		TextField<String> modelField = new TextField<>("model");
		modelField.setRequired(true);
		form.add(modelField);

	   	TextField<Integer> colPassangersBuisnesField = new TextField<>("colPassangersBuisnes");
		colPassangersBuisnesField.setRequired(true);
		form.add(colPassangersBuisnesField);
		
		TextField<Integer> colPassangersFirstclassField = new TextField<>("colPassangersFirstclass");
		colPassangersFirstclassField.setRequired(true);
		form.add(colPassangersFirstclassField);
		
		TextField<Integer> colPassangersEconomyField = new TextField<>("colPassangersEconomy");
		colPassangersEconomyField.setRequired(true);
		form.add(colPassangersEconomyField);
		
		TextField<Integer> weightAllBaggageField = new TextField<>("weightAllBaggage");
		weightAllBaggageField.setRequired(true);
		form.add(weightAllBaggageField);
		
		TextField<Integer> avgSpeedField = new TextField<>("avgSpeed");
		avgSpeedField.setRequired(true);
		form.add(avgSpeedField);
		
        DropDownChoice<ClassWeight> classWeightField = new DropDownChoice<>("classWeight", Arrays.asList(ClassWeight.values()), ClassWeightChoiceRenderer.INSTANCE);
        classWeightField.setRequired(true);
        form.add(classWeightField);

		
		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				
				if (modelPlane.getId() == null) {
					modelPlaneService.insert(modelPlane);
				} else {
					modelPlaneService.update(modelPlane);
				}

				setResponsePage(new ModelPlanePage());
			}
		});

		add(new FeedbackPanel("feedback"));

    	
    }
}
