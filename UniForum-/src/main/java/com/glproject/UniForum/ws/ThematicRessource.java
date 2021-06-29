package com.glproject.UniForum.ws;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.glproject.UniForum.dao.DAO;
import com.glproject.UniForum.dao.Thematic;

@Path("/Thematic")
public class ThematicRessource {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Response updateThematic(Thematic subject) {
		
		subject = DAO.getThematicDao().updateThematic(subject);	
		
		return Response.ok(subject).build();
	}
	
	/*
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addDiscu")
	public Response addThematic(Thematic subject) {
		
		subject = DAO.getThematicDao().addThematic(subject);	
		
		return Response.ok(subject).build();
	}*/
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Thematic getThematic(@PathParam("id") String id){
		
		long thematicid = Long.parseLong(id);
		
		return DAO.getThematicDao().getThematic(thematicid);	
	}
	
	/*
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void deleteThematic(Thematic subject){
		DAO.getThematicDao().deleteThematic(subject);
	}*/
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getall")
	public List<Thematic> getAllThematics() {
		return DAO.getThematicDao().getThematics();
	}
	
	

}
