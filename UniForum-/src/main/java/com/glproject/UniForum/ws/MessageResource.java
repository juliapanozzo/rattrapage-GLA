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
import com.glproject.UniForum.dao.Message;
import com.glproject.UniForum.dao.User;
import com.glproject.UniForum.dao.DiscussionThread;

@Path("/Message")
public class MessageResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Message getMessage(@PathParam("id") String id) {
		long messageid = Long.parseLong(id);
		
		Message message = DAO.getMessageDao().getMessage(messageid);
		
		return message;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/user")
	public List<Message> retrieveMessages(User u){
		return DAO.getMessageDao().getMessages(u);
	}
	
	
	/**
	 * Get the messages of a specific discussion thread
	 * @param id
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/fromDiscu/{id}")
	public List<Message> getDiscussionMessage(@PathParam("id") String id){
		long discussionid = Long.parseLong(id);
		
		DiscussionThread discu = DAO.getDiscussionThreadDao().getDiscussion(discussionid);
		List<Message> msg = discu.getAnswerMessage();
		msg.add(discu.getQuestion());
		
		return msg;
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/create/{discuId}")
	public Response createMessage(@PathParam("discuId") String discuId, Message msg) {

		Message detached = DAO.getMessageDao().addMessageTo(Long.parseLong(discuId), msg);
		
		return Response.ok(detached).build();		
	}

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Response updateMessage(Message msg) {	
		msg = DAO.getMessageDao().updateMessage(msg);
		return Response.ok(msg).build();
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public DiscussionThread removeMessage(@PathParam("id") String discuId, Message msg) {
		return DAO.getMessageDao().deleteMessageTo(Long.parseLong(discuId), msg);
	}

}








