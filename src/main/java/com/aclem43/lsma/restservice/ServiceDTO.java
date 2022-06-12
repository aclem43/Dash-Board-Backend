package com.aclem43.lsma.restservice;

public class ServiceDTO {
    public String getServiceName() {
        return serviceName;
    }

    public Action getAction() {
        return action;
    }

    private final String serviceName;
    private final Action action;

    public ServiceDTO(String serviceName, Action action) {
        this.serviceName = serviceName;
        this.action = action;
    }
}

