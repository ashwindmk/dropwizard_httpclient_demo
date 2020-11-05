package com.ashwin.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HttpClientConfiguration {
    @NotNull
    private int timeout = 500 * 1000;  // milliseconds

    @NotNull
    private int connectionTimeout = 500 * 1000;  // milliseconds

    @NotNull
    private int connectionRequestTimeout = 500 * 1000;  // milliseconds

    @NotNull
    private long timeToLive = 60 * 60 * 1000L;  // milliseconds

    @Min(1)
    @Max(10_000)
    private int maxConnections = 1024;

    @Min(1)
    @Max(4000)
    private int maxConnectionsPerRoute = 512;

    @NotNull
    private int keepAlive = 0;  // milliseconds

    @NotNull
    private int validateAfterInactivityPeriod = 1000;  // milliseconds

    @Min(0)
    @Max(1000)
    private int retries = 0;

    @NotNull
    private Optional<String> userAgent = Optional.empty();

    public HttpClientConfiguration() {
    }

    @JsonProperty
    public int getTimeout() {
        return this.timeout;
    }

    @JsonProperty
    public void setTimeout(int duration) {
        this.timeout = duration * 1000;
    }

    @JsonProperty
    public int getConnectionTimeout() {
        return this.connectionTimeout;
    }

    @JsonProperty
    public void setConnectionTimeout(int duration) {
        this.connectionTimeout = duration * 1000;
    }

    @JsonProperty
    public int getConnectionRequestTimeout() {
        return this.connectionRequestTimeout;
    }

    @JsonProperty
    public void setConnectionRequestTimeout(int connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout * 1000;
    }

    @JsonProperty
    public long getTimeToLive() {
        return this.timeToLive;
    }

    @JsonProperty
    public void setTimeToLive(long timeToLive) {
        this.timeToLive = timeToLive * 1000;
    }

    @JsonProperty
    public int getValidateAfterInactivityPeriod() {
        return validateAfterInactivityPeriod;
    }

    @JsonProperty
    public void setValidateAfterInactivityPeriod(int validateAfterInactivityPeriod) {
        this.validateAfterInactivityPeriod = validateAfterInactivityPeriod * 1000;
    }

    @JsonProperty
    public int getMaxConnections() {
        return this.maxConnections;
    }

    @JsonProperty
    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    @JsonProperty
    public int getMaxConnectionsPerRoute() {
        return this.maxConnectionsPerRoute;
    }

    @JsonProperty
    public void setMaxConnectionsPerRoute(int maxConnectionsPerRoute) {
        this.maxConnectionsPerRoute = maxConnectionsPerRoute;
    }

    @JsonProperty
    public int getRetries() {
        return this.retries;
    }

    @JsonProperty
    public void setRetries(int retries) {
        this.retries = retries;
    }

    public int getKeepAlive() {
        return this.keepAlive;
    }

    @JsonProperty
    public void setKeepAlive(int keepAlive) {
        this.keepAlive = keepAlive * 1000;
    }

    @JsonProperty
    public Optional<String> getUserAgent() {
        return this.userAgent;
    }

    @JsonProperty
    public void setUserAgent(Optional<String> userAgent) {
        this.userAgent = userAgent;
    }
}
