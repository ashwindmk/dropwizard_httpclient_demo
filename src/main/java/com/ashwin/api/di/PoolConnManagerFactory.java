package com.ashwin.api.di;

import com.ashwin.config.HttpClientDemoConfiguration;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.glassfish.hk2.api.Factory;

import javax.inject.Inject;

public class PoolConnManagerFactory implements Factory<PoolingHttpClientConnectionManager> {
    @Inject
    private HttpClientDemoConfiguration config;

    @Override
    public PoolingHttpClientConnectionManager provide() {
        PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager();
        httpClientConnectionManager.setMaxTotal(config.getHttpClientConfiguration().getMaxConnections());
        httpClientConnectionManager.setValidateAfterInactivity(config.getHttpClientConfiguration().getValidateAfterInactivityPeriod());
        return httpClientConnectionManager;
    }

    @Override
    public void dispose(PoolingHttpClientConnectionManager poolingHttpClientConnectionManager) {
        poolingHttpClientConnectionManager.close();
    }
}
