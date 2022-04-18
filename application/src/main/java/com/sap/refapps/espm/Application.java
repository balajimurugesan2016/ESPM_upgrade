package com.sap.refapps.espm;

import java.util.Arrays;

import com.sap.refapps.espm.config.CustomerApplicationContextInitializer;
import com.sap.refapps.espm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.Environment;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class Application implements CommandLineRunner {

    private static final String CUSTOMER_DATA_LOCATION = "/customer.json";

    @Autowired
    private Environment environment;

    @Autowired
    private CustomerService customerService;

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .initializers(new CustomerApplicationContextInitializer()).run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (Arrays.stream(environment.getActiveProfiles())
                .anyMatch(env -> (env.equalsIgnoreCase("local") || env.equalsIgnoreCase("cloud")))) {
            customerService.loadCustomer(CUSTOMER_DATA_LOCATION);
        }

    }

}
