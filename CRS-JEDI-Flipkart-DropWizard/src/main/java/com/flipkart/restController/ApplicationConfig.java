package com.flipkart.restController;

import org.glassfish.jersey.server.ResourceConfig;



public class ApplicationConfig extends ResourceConfig {
    public ApplicationConfig() {
        register(StudentRestAPI.class);
        register(UserRestAPI.class);
        register(ProfessorRestAPI.class);
        register(AdminRestAPI.class);
    }
}