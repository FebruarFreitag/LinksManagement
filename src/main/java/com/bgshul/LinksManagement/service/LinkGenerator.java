package com.bgshul.LinksManagement.service;

import javax.ws.rs.core.Response;

public class LinkGenerator {

    public Response generate(LinkJSON linkJSON){
        LinkEncoder linkEncoder = new LinkEncoder();
        LinkDB linkDB = new LinkDB();
        String shortName = linkEncoder.encodeLink();
        if (shortName!=("Error")) {
            linkDB.addNewLink(linkJSON.getOriginal(), shortName);
            String link = "{\n" +
                    "   \"link\": \"/l/" + shortName + "\"\n" +
                    "}";
            return Response.ok(link).build();
        }
        else {
            return Response.serverError().build();
        }
    }
}
