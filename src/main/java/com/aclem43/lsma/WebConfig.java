package com.aclem43.lsma;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;


@Configuration
@EnableConfigurationProperties({LsmaProperties.class})
public class WebConfig implements WebMvcConfigurer {

    private @Autowired LsmaProperties lsmaProperties;

    private static  final Logger log = LoggerFactory.getLogger(WebConfig.class);

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {


        registry.addResourceHandler("/static/fonts/**", "/favicon-*.png")
                .addResourceLocations("classpath:/public/static/fonts/", "classpath:/public/static/")
                .setCacheControl(CacheControl.empty().cachePublic());


        if (lsmaProperties.isServeResource()) {
            String r = lsmaProperties.getResourceFolder();



                String resourceLocation = r;

                if (!r.startsWith("file:")) {
                    // forcing to use file so that we don't end up
                    resourceLocation = "file:" + resourceLocation;
                }


            ResourceHandlerRegistration reg = registry.addResourceHandler("/", "", "/**")
                    .addResourceLocations(resourceLocation);
                reg.resourceChain(true).addResolver(new IndexHtmlAwarePathResourceResolver(Collections.singleton(r)));
            }
        }

    }

