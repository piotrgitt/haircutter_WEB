package com.jsfcourse.haircutter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.simplesecurity.RemoteClient;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jsf.haircutter.dao.UserDAO;
import jsf.haircutter.entities.User;

@Named
@RequestScoped
public class LoginBB {
	private String login;
	private String pass;
	
	private static final String PAGE_MAIN = "/public/index?faces-redirect=true";
	private static final String PAGE_LOGIN = "/public/login";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	
	@Inject
	FacesContext ctx;
	
	@Inject
	Flash flash;
	
	@EJB
	UserDAO userDAO;
	
	
	
	

	public String doLogin() {
		FacesContext ctx = FacesContext.getCurrentInstance();

		// 1. verify login and password - get User from "database"
		User user = userDAO.getUserFromDatabase(login);
		
		// 2. if bad login or password - stay with error info
		if (user == null) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Niepoprawny login lub has³o", null));
			return PAGE_STAY_AT_THE_SAME;
		} 
		if(login.equals(user.getLogin()) && pass.equals(user.getPassword())) {
			// 3. if logged in: get User roles, save in RemoteClient and store it in session
			
			RemoteClient<User> client = new RemoteClient<User>(); //create new RemoteClient
			client.setDetails(user);
			
			String role = user.getRole(); //get User roles 
			client.getRoles().add(role);

		
			//store RemoteClient with request info in session (needed for SecurityFilter)
			HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
			client.store(request);

			// and enter the system (now SecurityFilter will pass the request)
			return PAGE_MAIN;
		} else {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Niepoprawny login lub has³o", null));
			return PAGE_STAY_AT_THE_SAME;
			
		}

	}
	
	public String doLogout(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		//Invalidate session
		// - all objects within session will be destroyed
		// - new session will be created (with new ID)
		session.invalidate();
		return PAGE_LOGIN;
	}

	//GETTERS AND SETTERS



	
	public Object getCtx() {
		return ctx;	
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}



}
