package by.bogdevich.training.airline.webapp.component.menu;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import by.bogdevich.training.airline.webapp.page.registr.RegistrPage;


public class Loger extends Panel {

    public Loger(String id) {
        super(id);
        // setRenderBodyOnly(true);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new Link("registration") {
            @Override
            public void onClick() {
                setResponsePage(new RegistrPage());
            }
        });

     }
}
