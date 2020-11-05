package com.ashwin.api.di;

import com.ashwin.api.resource.HttpClientDemoResource;
import com.ashwin.api.resource.PingResource;
import com.ashwin.config.HttpClientDemoConfiguration;
import io.dropwizard.setup.Environment;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

public class HttpClientModule extends AbstractBinder {
    private HttpClientDemoConfiguration config;
    private Environment env;

    public HttpClientModule(HttpClientDemoConfiguration config, Environment env) {
        this.config = config;
        this.env = env;
    }

    @Override
    protected void configure() {
        bind(this.config).to(HttpClientDemoConfiguration.class);
        bind(this.env).to(Environment.class);

        bindFactory(PoolConnManagerFactory.class).to(PoolingHttpClientConnectionManager.class).in(Singleton.class);
        bindFactory(HttpClientFactory.class).to(HttpClient.class).named("http-client").in(Singleton.class);

        bind(PingResource.class).to(PingResource.class).in(Singleton.class);
        bind(HttpClientDemoResource.class).to(HttpClientDemoResource.class).in(Singleton.class);
    }
}
