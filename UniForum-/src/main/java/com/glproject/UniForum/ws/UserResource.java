package com.glproject.UniForum.ws;

import java.util.ArrayList;
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
import com.glproject.UniForum.dao.User;

@Path("/User")
public class UserResource {
	
	/**
	 * The ID of the last user connected:
	 */
	static Long lastConnection = null;
	

	/**
	 * 
	 * @param data has to be like: [username,password]
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/connection")
	public Boolean retrieveIdentity(String[] data) {
		
		String username = data[0];
		String password = data[1];
		
		List<User> usersRegistered = DAO.getUserDao().getUsers(username);
		
		for (User user : usersRegistered) {
			if (user.hasPassword(password)) {
				lastConnection = user.getId();
				return true;
			}
		}	
		return false;
	}
	
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/name")
	public List<User> retrieveUserByName(String name){
		
		List<User> users = DAO.getUserDao().getUsers();
		List<User> matchingUsers = new ArrayList<User>();
		
		for (User user : users) {
			if (user.getUserame().toLowerCase().matches("(.*)"+name.toLowerCase()+"(.*)")) {
				matchingUsers.add(user);
			}
		}
		return matchingUsers;
	}
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public User getUser(@PathParam("id") String id) {	
		return DAO.getUserDao().getUser(Long.parseLong(id));
	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/currentSession")
	public User getCurrentUser() {
		if (lastConnection==null) {
			return null;
		}
		User user = DAO.getUserDao().getUser(lastConnection);	
		return user;
	}
	
	
	
	/**
	 * 
	 * @param data has to be like: [username,password]
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/create")	
	public Response createUser(Object[] data) {
		
		String username = (String) data[0];
		String password = (String) data[1];
		
		User newUser = new User(username, password);
		newUser = DAO.getUserDao().addUser(newUser);
					
		lastConnection = newUser.getId();
		
		return Response.ok(newUser).build();
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteUser(User user) {
		DAO.getUserDao().delete(user);
	}
}










