package com.example.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
    @Value("${name}")
    private String name;

    @Value("${remote.foo}")
    private String cloudValue1;

    @Value("${foo}")
    private String localOverride;

    @RequestMapping("/")
    public String hello(){
        return String.format("Hello %s, foo's %s, overrides: %s", name, cloudValue1, localOverride);
    }
}
