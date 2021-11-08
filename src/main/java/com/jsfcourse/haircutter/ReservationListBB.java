package com.jsfcourse.haircutter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import jsf.haircutter.dao.ReservationDAO;
import jsf.haircutter.entities.Reservation;
import jsf.haircutter.entities.User;

@Named
@ViewScoped
public class ReservationListBB implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2296984902550040713L;
	private String name;
	private String phoneNumber;
	private int idReservation;
	private String time;

//	private static final String PAGE_PERSON_EDIT = "personEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	@Inject
	FacesContext ctx;
	
	@Inject
	Flash flash;
	
	@EJB
	ReservationDAO reservationDAO;
	
	
	
	
	public List<Reservation> getFullList(){
		return reservationDAO.getFullList();
	}

	public List<Reservation> getList(){
		List<Reservation> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (name != null && name.length() > 0){
			searchParams.put("name", name);
		}
		
		//2. Get list
		list = reservationDAO.getList(searchParams);
		
		return list;
	}
	
	public List<Reservation> getUserReservationList(User user){
		List<Reservation> list = null;
		
		list = reservationDAO.getUserReservationList(user);
		
		return list;
	}
	
	
	
	
//	public String newReservation(){
//		Reservation person = new Reservation();
//		
//		//1. Pass object through session
//		//HttpSession session = (HttpSession) extcontext.getSession(true);
//		//session.setAttribute("person", person);
//		
//		//2. Pass object through flash	
//		flash.put("reservation", reservation);
//		
//		return PAGE_PERSON_EDIT;
//	}

//	public String editPerson(Reservation reservation){
//		//1. Pass object through session
//		//HttpSession session = (HttpSession) extcontext.getSession(true);
//		//session.setAttribute("person", person);
//		
//		//2. Pass object through flash 
//		flash.put("reservation", reservation);
//		
//		return PAGE_PERSON_EDIT;
//	}

	public String deleteReservation(Reservation reservation){
		reservationDAO.remove(reservation);
		return PAGE_STAY_AT_THE_SAME;
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
