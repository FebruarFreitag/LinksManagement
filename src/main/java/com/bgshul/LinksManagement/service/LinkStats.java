package com.bgshul.LinksManagement.service;

import javax.ws.rs.core.Response;

public class LinkStats {

    public Response singleLinkStats(String link){
        LinkDB linkDB = new LinkDB();
        LinkJSON linkJSON = linkDB.getSingleLinkStats(link);
        if (linkJSON.getRank()!=0) {
            return Response.ok(linkJSON).build();
        }
        else {
            return Response.serverError().build();
        }
    }

    public Response linksStats(int page, int count){
        LinkDB linkDB = new LinkDB();
        int counter = 1;
        StringBuilder stringBuilder = new StringBuilder("[ ");
        while (counter<=count){
            stringBuilder.append(linkDB.getLinksStats(page, count, counter));
            if (counter<count & linkDB.getLinksStats(page, count, counter+1).getCount()!=0){
                stringBuilder.append(",");
            }
            else break;
            counter++;
        }
        stringBuilder.append("]");
        return Response.ok(stringBuilder.toString()).build();
    }
}
