package com.ashwin.api.di;

import com.ashwin.config.HttpClientConfiguration;
import com.ashwin.config.HttpClientDemoConfiguration;
import io.dropwizard.setup.Environment;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.glassfish.hk2.api.Factory;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

public class HttpClientFactory implements Factory<HttpClient> {
    private HttpClientDemoConfiguration config;
    private Environment env;

    @Inject
    PoolingHttpClientConnectionManager poolConnManager;

    @Inject
    public HttpClientFactory(HttpClientDemoConfiguration config, Environment env) {
        this.config = config;
        this.env = env;
    }

    @Override
    public HttpClient provide() {
        HttpClientConfiguration httpConfig = config.getHttpClientConfiguration();

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(httpConfig.getTimeout())
                .setConnectTimeout(httpConfig.getConnectionTimeout())
                .setConnectionRequestTimeout(httpConfig.getConnectionRequestTimeout())
                .build();

        ConnectionKeepAliveStrategy keepAliveStrategy = new DefaultConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                long keepAlive = super.getKeepAliveDuration(response, context);
                if (keepAlive == -1) {
                    keepAlive = httpConfig.getKeepAlive();
                }
                System.out.println("ConnectionKeepAliveStrategy | keepAlive: " + keepAlive);
                return keepAlive;
            }
        };

        CloseableHttpClient client = HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(poolConnManager)
                .setMaxConnTotal(httpConfig.getMaxConnections())
                .setMaxConnPerRoute(httpConfig.getMaxConnectionsPerRoute())
                //.setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)
                .setKeepAliveStrategy(keepAliveStrategy)
                .setConnectionTimeToLive(httpConfig.getTimeToLive(), TimeUnit.MILLISECONDS)
                .build();

        return client;
    }

    @Override
    public void dispose(HttpClient client) {
        System.out.println("HttpClientFactory | dispose | client: " + client);
    }
}
