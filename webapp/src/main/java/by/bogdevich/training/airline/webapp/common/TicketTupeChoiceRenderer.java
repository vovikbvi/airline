package by.bogdevich.training.airline.webapp.common;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.bogdevich.training.airline.datamodel.TicketTupe;

public class TicketTupeChoiceRenderer extends ChoiceRenderer<TicketTupe>{
	
	public static final TicketTupeChoiceRenderer INSTANCE = new TicketTupeChoiceRenderer();

	
	
	public TicketTupeChoiceRenderer() {
		super();
	}


	@Override
	public Object getDisplayValue(TicketTupe object) {
		return object.name();
	}

	@Override
	public String getIdValue(TicketTupe object, int index) {
		return String.valueOf(object.ordinal());
	}

}
