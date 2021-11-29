package com.westlakefinancial.technology.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * @ClassName SwaggerProperties
 * @Description TODO
 * @Author james
 * @Date 2021/11/25 14:45
 * @Version 1.0
 **/

@Component
@ConfigurationProperties("swagger")
public class SwaggerProperties {
    /**
     * Whether to open swagger, the production environment is generally closed, so define a variable here
     */
    private Boolean enable;

    /**
     * Project application name
     */
    private String applicationName;

    /**
     * Project version information
     */
    private String applicationVersion;

    /**
     * Project description information
     */
    private String applicationDescription;

    /**
     * Interface debugging address
     */
    private String tryHost;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationVersion() {
        return applicationVersion;
    }

    public void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    public String getApplicationDescription() {
        return applicationDescription;
    }

    public void setApplicationDescription(String applicationDescription) {
        this.applicationDescription = applicationDescription;
    }

    public String getTryHost() {
        return tryHost;
    }

    public void setTryHost(String tryHost) {
        this.tryHost = tryHost;
    }
}