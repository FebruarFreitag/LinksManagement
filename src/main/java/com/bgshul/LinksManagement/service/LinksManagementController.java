package com.bgshul.LinksManagement.service;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/LinksManagement")
public class LinksManagementController {

    @Path("/generate")
    @POST
    @Consumes ("application/json")
    @Produces ("application/json")
    public Response generate(LinkJSON linkJSON){
        LinkGenerator linkGenerator = new LinkGenerator();
        return linkGenerator.generate(linkJSON);
    }

    @Path("/l/{shortName}")
    @GET
    public Response redirect(@PathParam("shortName") String shortName){
            LinkRedirect linkRedirect = new LinkRedirect();
            return linkRedirect.redirect(shortName);
    }

    @Path("/stats/{shortName}")
    @GET
    @Produces ("application/json")
    public Response generate(@PathParam("shortName") String shortName){
        LinkStats linkStats = new LinkStats();
        return linkStats.singleLinkStats(shortName);
    }

    @Path("/stats")
    @GET
    @Produces ("application/json")
    public Response generate(@QueryParam("page") int page,
                             @QueryParam("count") int count){
        LinkStats linkStats = new LinkStats();
        return linkStats.linksStats(page,count);
    }
}
