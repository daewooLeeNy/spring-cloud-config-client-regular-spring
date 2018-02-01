package com.example.client;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * Created by 1002529 on 1/25/18.
 */
@PropertySource(value="classpath:bootstrap.properties")
public class MyConfigurableWebApplicationContext extends XmlWebApplicationContext {
    @Override
    protected ConfigurableEnvironment createEnvironment() {
        return new CloudEnvironment();
    }
}
