package com.bgshul.LinksManagement.service;

import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

public class LinkRedirect {

    public Response redirect(String shortname) {
        LinkDB linkDB = new LinkDB();
        String original = linkDB.updateCount(shortname);
        if (original!="Error") {
            try {
                URI uriForRedirection = new URI(original);
                return Response.seeOther(uriForRedirection).build();
            } catch (URISyntaxException e) {
                e.printStackTrace();
                return Response.serverError().build();
            }
        }
        else {
            return Response.serverError().build();
        }
    }
}
