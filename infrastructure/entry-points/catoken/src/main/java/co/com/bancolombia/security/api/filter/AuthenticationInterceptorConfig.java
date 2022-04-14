package co.com.bancolombia.security.api.filter;

import co.com.bancolombia.security.model.util.PathConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Component
public class AuthenticationInterceptorConfig extends WebMvcConfigurationSupport {

   @Autowired
   @Qualifier("validateGlobalInterceptor")
   HandlerInterceptor authenticationInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        String pathPattern = PathConstant.BASE_URL +"/**";
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns(pathPattern)
                .excludePathPatterns( "swagger-ui.html",
                                      "/swagger-ui/",
                                      "static/css/**",
                                      "static/js/**",
                                      "swagger-resources",
                                      "/error/**",
                                      "v2/api-docs");

    }

}
