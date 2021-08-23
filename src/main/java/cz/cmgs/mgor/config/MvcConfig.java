package cz.cmgs.mgor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**", "/webjars/**").addResourceLocations("classpath:/static/", "classpath:/webjars/", "classpath:/META-INF/resources/webjars/");

        super.addResourceHandlers(registry);
    }
}
