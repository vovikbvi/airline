package by.bogdevich.training.airline.webapp.page.admin.price;

import javax.inject.Inject;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import by.bogdevich.training.airline.datamodel.Price;
import by.bogdevich.training.airline.service.PriceService;
import by.bogdevich.training.airline.webapp.page.AbstractPage;


@AuthorizeInstantiation(value = {"ADMIN", "OPERATOR"})
public class PriceEditPage extends AbstractPage {
	
	@Inject
	private PriceService priceService;
	
	private Price price; 
	

    public PriceEditPage() {
        super();
      }


	public PriceEditPage(Price price) {
		super();
		this.price = price;
	}

    @Override
    protected void onInitialize() {
    	super.onInitialize();
    	
		Form form = new Form("form", new CompoundPropertyModel<Price>(price));
		add(form);
  

		DateTextField dateChangeField = new DateTextField("dataChange", "dd-MM-yyyy");
		dateChangeField.add(new DatePicker());
        dateChangeField.setRequired(true);
		form.add(dateChangeField);
		
    	TextField<Double> basicPriceField = new TextField<>("basicPrice");
		basicPriceField.setRequired(true);
		form.add(basicPriceField);
		
		
		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				
				if (price.getId() == null) {
					priceService.insert(price);
				} else {
					priceService.update(price);
				}

				setResponsePage(new PricesPage());
			}
		});

		add(new FeedbackPanel("feedback"));

    	
    	
    }
}
