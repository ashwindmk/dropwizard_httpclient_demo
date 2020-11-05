package com.ashwin.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HttpClientDemoConfiguration extends Configuration {
    @Valid
    @NotNull
    @JsonProperty("httpClient")
    private HttpClientConfiguration httpClientConfiguration = new HttpClientConfiguration();

    public HttpClientConfiguration getHttpClientConfiguration() {
        return httpClientConfiguration;
    }
}
