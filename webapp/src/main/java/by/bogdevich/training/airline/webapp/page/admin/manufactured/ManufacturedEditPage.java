package by.bogdevich.training.airline.webapp.page.admin.manufactured;

import javax.inject.Inject;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import by.bogdevich.training.airline.datamodel.ManufacturedPlane;
import by.bogdevich.training.airline.service.ManufacturedPlainService;
import by.bogdevich.training.airline.webapp.page.AbstractPage;

@AuthorizeInstantiation(value = {"ADMIN", "OPERATOR"})
public class ManufacturedEditPage extends AbstractPage {

	@Inject
	private ManufacturedPlainService manufacturedPlainService;

	private ManufacturedPlane manufacturedPlane;

	public ManufacturedEditPage() {
		super();
	}

	public ManufacturedEditPage(ManufacturedPlane manufacturedPlane) {
		super();
		this.manufacturedPlane = manufacturedPlane;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		Form form = new Form("form", new CompoundPropertyModel<ManufacturedPlane>(manufacturedPlane));
		add(form);
  
    	TextField<String> nameField = new TextField<>("name");
		nameField.setRequired(true);
		form.add(nameField);
		
		
		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				
				if (manufacturedPlane.getId() == null) {
					manufacturedPlainService.insert(manufacturedPlane);
				} else {
					manufacturedPlainService.update(manufacturedPlane);
				}

				setResponsePage(new ManufacturedPage());
			}
		});

		add(new FeedbackPanel("feedback"));
	
		
	}

}
