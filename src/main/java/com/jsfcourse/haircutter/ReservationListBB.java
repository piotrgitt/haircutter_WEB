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


import jsf.haircutter.dao.ReservationDAO;
import jsf.haircutter.entities.Reservation;

@Named
@RequestScoped
public class ReservationListBB {
	private String name;
	private String phoneNumber;
	private int idReservation;
	private String time;


	@Inject
	FacesContext ctx;
	
	@Inject
	Flash flash;
	
	@EJB
	ReservationDAO reservationDAO;
	
	
	
	
	public List<Reservation> getFullList(){
		return reservationDAO.getFullList();
	}

//	public List<Reservation> getList(){
//		List<Reservation> list = null;
//		
//		//1. Prepare search params
//		Map<String,Object> searchParams = new HashMap<String, Object>();
//		
//		if (name != null && name.length() > 0){
//			searchParams.put("name", name);
//		}
//		
//		//2. Get list
//		list = userDAO.getList(searchParams);
//		
//		return list;
//	}

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

	public int getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}


}
