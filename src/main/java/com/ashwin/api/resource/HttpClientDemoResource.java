package com.ashwin.api.resource;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.pool.PoolStats;
import org.apache.http.util.EntityUtils;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Arrays;

@Path("/httpclient")
public class HttpClientDemoResource {
    @Inject
    @Named("http-client")
    HttpClient httpClient;

    @Inject
    PoolingHttpClientConnectionManager poolingHttpClientConnectionManager;

    @GET
    @Path("/")
    public Response ping() {
        HttpGet httpget = new HttpGet("http://localhost:9090/process");
        String body = "default_message";
        try {
            HttpResponse response = httpClient.execute(httpget);
            int status = response.getStatusLine().getStatusCode();
            String reason = response.getStatusLine().getReasonPhrase();
            body = EntityUtils.toString(response.getEntity());

            Header[] headers = response.getAllHeaders();
            String headerStr = Arrays.toString(headers);

            System.out.println("HttpClientDemoResource | status: " + status + " | reason: " + reason
                    + " | headers: " + headerStr
                    + " | body: " + body);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Response.ok(body).build();
    }

    @GET
    @Path("/stats")
    public Response stats() {
        PoolStats poolStats = poolingHttpClientConnectionManager.getTotalStats();
        System.out.println("stats | poolStats: " + poolStats);
        return Response.ok(String.valueOf(poolStats)).build();
    }
}
