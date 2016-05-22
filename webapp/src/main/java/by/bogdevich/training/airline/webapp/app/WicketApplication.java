package by.bogdevich.training.airline.webapp.app;

import javax.inject.Inject;

import org.apache.wicket.ConverterLocator;
import org.apache.wicket.IConverterLocator;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import by.bogdevich.training.airline.webapp.page.home.HomePage;
import by.bogdevich.training.airline.webapp.util.LocalDateConverter;




@Component("wicketWebApplicationBean")
public class WicketApplication extends WebApplication {
    @Inject
    private ApplicationContext applicationContext;

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
        super.init();
        getMarkupSettings().setStripWicketTags(true);
        // add your configuration here
        
        getComponentInstantiationListeners().add(new SpringComponentInjector(this, getApplicationContext()));

        // mount
       // mountPage(path, pageClass)("/productDetails", ProductDetailsPage.class);

    }
    
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
/*
    @Override
    protected IConverterLocator newConverterLocator() {
        ConverterLocator locator = (ConverterLocator) super.newConverterLocator();
        locator.set(Double.class, new LocalDateConverter());
        return locator;
    }
*/
}