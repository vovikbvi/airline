package by.bogdevich.training.airline.webapp.common.renderer;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.bogdevich.training.airline.datamodel.TicketClass;

public class TicketClassChoiceRenderer extends ChoiceRenderer<TicketClass> {

	public static final TicketClassChoiceRenderer INSTANCE = new TicketClassChoiceRenderer();

    @Override
    public Object getDisplayValue(TicketClass object) {
        return object.name();
    }

    @Override
    public String getIdValue(TicketClass object, int index) {
        return String.valueOf(object.ordinal());
    }

}
