package by.bogdevich.training.airline.webapp.page.feedback;


import javax.inject.Inject;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

import by.bogdevich.training.airline.service.SemdMail;
import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.schedule.SchedulePage;

public class FeedbackPage extends AbstractPage {

	@Inject
	private SemdMail semdMail;
	
	private String contactName;
	private String contactMail;
	private String contactPhone;
	private String contactMessage;
	
	public FeedbackPage() {
		super();
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		Form<?> form = new Form<Void>("form");
		add(form);
		form.setDefaultModel(new CompoundPropertyModel(this));
		
		TextField<String> contactNameField = new TextField<String>("contactName");
		contactNameField.setRequired(true);
		form.add(contactNameField);
		
		TextField<String> contactMailField = new TextField<String>("contactMail");
		contactMailField.setRequired(true);
		form.add(contactMailField);
		
		TextField<String> contactPhoneField = new TextField<String>("contactPhone");
		contactPhoneField.setRequired(true);
		form.add(contactPhoneField);
		
	    TextArea<String> contactMessageField = new TextArea<String>("contactMessage");
		contactMessageField.setRequired(true);
		form.add(contactMessageField);
		
		form.add(new SubmitLink("send") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				

				String toAddr = "LowCostAirlineService@gmail.com";
				String fromAddr = "LowCostAirlineService@gmail.com";
				String subject = contactName;
				String body = String.format("%s \n %s \n %s", contactPhone, contactMail, contactMessage);
				semdMail.crunchifyReadyToSendEmail(toAddr, fromAddr, subject, body);

	
				}
		});


	}
}
