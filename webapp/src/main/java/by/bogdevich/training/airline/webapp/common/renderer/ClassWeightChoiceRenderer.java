package by.bogdevich.training.airline.webapp.common.renderer;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.bogdevich.training.airline.datamodel.ClassWeight;
import by.bogdevich.training.airline.datamodel.TicketClass;

public class ClassWeightChoiceRenderer extends ChoiceRenderer<ClassWeight> {

	public static final ClassWeightChoiceRenderer INSTANCE = new ClassWeightChoiceRenderer();

    @Override
    public Object getDisplayValue(ClassWeight object) {
        return object.name();
    }

    @Override
    public String getIdValue(ClassWeight object, int index) {
        return String.valueOf(object.ordinal());
    }

}
