package by.bogdevich.training.airline.webapp.page.home;

import org.apache.wicket.markup.html.link.Link;

import by.bogdevich.training.airline.webapp.page.AbstractPage;



public class HomePage extends AbstractPage {

    public HomePage() {
        super();
        add(new Link("linkproduct") {
            @Override
            public void onClick() {
               // setResponsePage(new ProductPage());
            }
        });
    }

}
