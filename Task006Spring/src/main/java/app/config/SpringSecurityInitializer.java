package app.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
        FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
        filter.setInitParameter("encoding", "UTF-8");
        filter.setInitParameter("forceEncoding", "true");
        filter.addMappingForUrlPatterns(null, false, "/*");

        super.beforeSpringSecurityFilterChain(servletContext);
    }
}



