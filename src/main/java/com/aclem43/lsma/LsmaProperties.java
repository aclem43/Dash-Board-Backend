package com.aclem43.lsma;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("lsma")
public class LsmaProperties {


    private boolean serveResource = true;
    private String resourceFolder = "C:\\Users\\Alexi\\Documents\\Programing\\Code\\Java\\lsma\\frontend\\lsma-frontend\\dist\\";

    public String getResourceFolder() {
        return resourceFolder;
    }

    public void setResourceFolder(String resourceFolder) {
        this.resourceFolder = resourceFolder;
    }

    public boolean isServeResource() {
        return serveResource;
    }

    public void setServeResource(boolean serveResource) {
        this.serveResource = serveResource;
    }
}
