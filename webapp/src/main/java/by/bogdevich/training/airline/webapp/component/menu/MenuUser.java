package by.bogdevich.training.airline.webapp.component.menu;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import by.bogdevich.training.airline.webapp.page.feedback.FeedbackPage;
import by.bogdevich.training.airline.webapp.page.home.HomePage;
import by.bogdevich.training.airline.webapp.page.myoders.MyOdersPage;
import by.bogdevich.training.airline.webapp.page.profile.ProfilePage;
import by.bogdevich.training.airline.webapp.page.registr.RegistrPage;
import by.bogdevich.training.airline.webapp.page.schedule.SchedulePage;
import by.bogdevich.training.airline.webapp.page.search.SearchPage;


public class MenuUser extends Panel {

    public MenuUser(String id) {
        super(id);
        // setRenderBodyOnly(true);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new Link("home-page") {
            @Override
            public void onClick() {
                setResponsePage(new HomePage());
            }
        });

        add(new Link("schedule") {
            @Override
            public void onClick() {
                setResponsePage(new SchedulePage());
            }
        });

        add(new Link("search") {
            @Override
            public void onClick() {
                setResponsePage(new SearchPage());
            }
        });

        add(new Link("my-oders") {
            @Override
            public void onClick() {
                setResponsePage(new MyOdersPage());
            }
        });

        add(new Link("my-profile") {
            @Override
            public void onClick() {
                setResponsePage(new ProfilePage());
            }
        });

        add(new Link("Feedback") {
            @Override
            public void onClick() {
                setResponsePage(new FeedbackPage());
            }
        });
                      
        add(new Link("registration") {
            @Override
            public void onClick() {
                setResponsePage(new RegistrPage());
            }
        });

    }
}
