package cz.muni.fi.pa165.web.config;

import cz.muni.fi.pa165.web.filter.CharacterEncodingFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


/**
 * Created by Marek Perichta.
 */
public class AppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext sc) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        //Register context
        ctx.register(WebApplicationConfiguration.class);
        //Encoding filter
        sc.addFilter("charEncodingFilter", CharacterEncodingFilter.class);
        ctx.setServletContext(sc);
        ServletRegistration.Dynamic servlet = sc.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }

}
