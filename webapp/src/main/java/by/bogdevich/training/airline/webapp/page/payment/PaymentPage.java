package by.bogdevich.training.airline.webapp.page.payment;

import javax.inject.Inject;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import by.bogdevich.training.airline.datamodel.Ticket;
import by.bogdevich.training.airline.service.TicketService;
import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.home.HomePage;



public class PaymentPage extends AbstractPage {

	@Inject
	private TicketService ticketService;
	
	private Ticket ticket;

	
    public PaymentPage() {
        super();
    }

 
    public PaymentPage(Ticket ticket) {
		super();
		this.ticket = ticket;
	}



	@Override
    protected void onInitialize() {
    	super.onInitialize();
    	
		Form form = new Form("form", new CompoundPropertyModel<Ticket>(ticket));
		add(form);
  
		
		Label ticketCosts = new Label("costs");
		form.add(ticketCosts);

    	ticket.setPaid(true);
    	
		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				
					ticketService.update(ticket);

				setResponsePage(new HomePage());
			}
		});

		add(new FeedbackPanel("feedback"));

    
    }
}
