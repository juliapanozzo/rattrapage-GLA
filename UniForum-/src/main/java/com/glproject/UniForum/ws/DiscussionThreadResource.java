package com.glproject.UniForum.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.glproject.UniForum.dao.DAO;
import com.glproject.UniForum.dao.DiscussionThread;

@Path("/Discussion")
public class DiscussionThreadResource {
	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Response updateDiscussion(DiscussionThread discu) {
		
		discu = DAO.getDiscussionThreadDao().updateDiscussion(discu);	
		
		return Response.ok(discu).build();
	}
		
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addDiscu/{thematicId}")
	public Response addDiscuTo(@PathParam("thematicId") String thematicId, DiscussionThread discu) {
		
		discu = DAO.getDiscussionThreadDao().addDiscussionTo(Long.parseLong(thematicId), discu);	
		
		return Response.ok(discu).build();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public DiscussionThread getDiscussion(@PathParam("id") String id){
		
		long discussionid = Long.parseLong(id);
		
		return DAO.getDiscussionThreadDao().getDiscussion(discussionid);
	
	}
	
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public List<DiscussionThread> removeDiscussion(@PathParam("id") String thematicId, DiscussionThread discu){
		return DAO.getDiscussionThreadDao().deleteDiscussionTo(Long.parseLong(thematicId), discu).getDiscussions();
	}
	
}
