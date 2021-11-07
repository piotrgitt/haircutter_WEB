package com.jsfcourse.haircutter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;


import jsf.haircutter.dao.UserDAO;
import jsf.haircutter.entities.User;

@Named
@RequestScoped
public class RegisterBB {
	private String passwordConfirmation;

	private User user = new User();
	
	private static final String PAGE_REGISTER_LIST = "register?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	
	@Inject
	FacesContext ctx;
	
	@Inject
	Flash flash;
	
	@EJB
	UserDAO userDAO;
	
	
	
	
	
	//save user to databases
	public String saveData() {
		try {
			if((user.getPassword()).equals(passwordConfirmation)) {
				user.setAddress("DEFAULT");
				user.setRole("user");
				userDAO.create(user);
				
			} else {
				
				ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Has³a musz¹ byæ takie same!", null));
					return PAGE_STAY_AT_THE_SAME;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wyst¹pi³‚ b³¹d podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_REGISTER_LIST;
	}
	

	//GETTERS AND SETTERS


	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}


	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	
	public User getUser() {
		return user;
	}
	
	public Object getCtx() {
		return ctx;	
	}



}
