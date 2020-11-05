package com.ashwin.api.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/")
public class PingResource {
    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok("pong").build();
    }
}
