package com.ashwin;

import com.ashwin.api.di.HttpClientModule;
import com.ashwin.api.resource.HttpClientDemoResource;
import com.ashwin.api.resource.PingResource;
import com.ashwin.config.HttpClientDemoConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class HttpClientDemoApplication extends Application<HttpClientDemoConfiguration> {
    public static void main(String[] args) throws Exception {
        new HttpClientDemoApplication().run(args);
    }

    @Override
    public void run(HttpClientDemoConfiguration config, Environment env) throws Exception {
        env.jersey().getResourceConfig().register(new HttpClientModule(config, env));

        env.jersey().register(PingResource.class);
        env.jersey().register(HttpClientDemoResource.class);
    }
}
