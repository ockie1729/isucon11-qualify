package isucon11_qualify.isucondition.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
class ResourceConfig implements WebMvcConfigurer {

    private static final String[] RESOURCE_LOCATIONS = {
            "file:../public/assets/"
    };

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**")
                .addResourceLocations(RESOURCE_LOCATIONS);
    }
}
