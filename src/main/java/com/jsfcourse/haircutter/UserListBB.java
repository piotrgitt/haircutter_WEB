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
public class UserListBB {
	private String name;
	private String phoneNumber;


	@Inject
	FacesContext ctx;
	
	@Inject
	Flash flash;
	
	@EJB
	UserDAO userDAO;
	
	
	
	
	public List<User> getFullList(){
		return userDAO.getFullList();
	}

	public List<User> getList(){
		List<User> user_list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (name != null && name.length() > 0){
			searchParams.put("name", name);
		}
		
		//2. Get list
		user_list = userDAO.getList(searchParams);
		
		return user_list;
	}

	//GETTERS AND SETTERS
	public String getPhone_number() {
		return phoneNumber;
	}

	public void setPhone_number(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
