package com.example.client;

import org.springframework.cloud.config.client.ConfigClientProperties;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.context.support.StandardServletEnvironment;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

/**
 * Created by 1002529 on 1/25/18.
 */
public class CloudEnvironment extends StandardServletEnvironment {
    @Override
    protected void customizePropertySources(MutablePropertySources propertySources) {
        super.customizePropertySources(propertySources);
        propertySources.addFirst(loadCustomBootstrapPropertySource());

        try {
            propertySources.addLast(initConfigServicePropertySourceLocator(this));
        } catch (Exception ex) {
            logger.warn("failed to initialize cloud config environment", ex);
        }
    }

    private PropertySource<?> initConfigServicePropertySourceLocator(Environment environment) {
        ConfigClientProperties configClientProperties = new ConfigClientProperties(environment);
        configClientProperties.setUri("http://localhost:8888");
        configClientProperties.setProfile("development");

        ConfigServicePropertySourceLocator configServicePropertySourceLocator = new ConfigServicePropertySourceLocator(configClientProperties);
        PropertySource<?> propertySource = configServicePropertySourceLocator.locate(environment);

        return propertySource;
    }

    private MapPropertySource loadCustomBootstrapPropertySource() {
        Resource resource = new ClassPathResource("bootstrap.properties");
        Properties properties = new Properties();
        try {
            properties = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            logger.debug("load file." + e.getMessage());
        }

        Map<String, Object> map = new java.util.HashMap<String, Object> ();
        Enumeration<String> enums = (Enumeration<String>)properties.propertyNames();
        while(enums.hasMoreElements()) {
            String key = enums.nextElement();
            map.put(key, properties.getProperty(key));
        }

        MapPropertySource propertySource = new MapPropertySource("applicationProperties", map);
        return propertySource;
    }
}
