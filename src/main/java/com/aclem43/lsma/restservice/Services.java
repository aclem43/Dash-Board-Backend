package com.aclem43.lsma.restservice;

import java.util.List;

public class Services {

    private final List<Service> services;

    public Services(List<Service> services) {
        this.services = services;
    }

    public List<Service> getServices() {
        return services;
    }
}
