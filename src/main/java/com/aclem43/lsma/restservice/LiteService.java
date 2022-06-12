package com.aclem43.lsma.restservice;

public class LiteService {

    private final String serviceName;

    private final int serviceStatus;

    public LiteService(String service_name, int status) {
        this.serviceName = service_name;
        this.serviceStatus = status;
    }

}
