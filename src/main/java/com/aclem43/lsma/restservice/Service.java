package com.aclem43.lsma.restservice;

public class Service {
    private final String serviceName;
    private final String serviceDisplayName;

    private final int status;

    public Service(String service_name, String service_display_name, int status) {
        this.serviceName = service_name;
        this.serviceDisplayName = service_display_name;
        this.status = status;
    }



    public String getServiceName() {
        return serviceName;
    }

    public String getServiceDisplayName() {
        return serviceDisplayName;
    }

    public int getStatus() {
        return status;
    }
}
