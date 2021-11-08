package com.jsfcourse.haircutter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


import org.primefaces.event.SelectEvent;

import jsf.haircutter.dao.ReservationDAO;
import jsf.haircutter.dao.ServiceDAO;
import jsf.haircutter.entities.Service;
import jsf.haircutter.entities.User;
import jsf.haircutter.entities.Reservation;

@Named
@SessionScoped
public class ReservationBB implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3791871974564937787L;
	private static final String PAGE_THANKS = "/public/thanks?faces-redirect=true";
	private static final String PAGE_CHOOSE_TIME = "/pages/userPages/temp_reservation?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private boolean servicesRendered  =  true;
	private boolean dateSet = false;
	private boolean timeSet = false;
	
	//SETTING CALENDAR
	private Date date;
	private Date dateTime;
	private Date minDateTime;
	private Date maxDateTime;
	
   
	@Inject
	FacesContext ctx;

	@Inject
	Flash flash;

	@EJB
	ServiceDAO serviceDAO;
	
	@EJB
	ReservationDAO reservationDAO;
	
	private Service service;
	private Reservation reservation;

	public List<Service> getServiceList() {
		return serviceDAO.getFullList();
	}

	public String newReservation(Service s, User remoteClient) {
		service = s;
		reservation = new Reservation();
		reservation.setReservationDescription(service.getServiceName());
		reservation.setService(service);
		reservation.setUser(remoteClient);
		reservation.setDuration(null);
		this.setServicesRendered(false);
		return PAGE_STAY_AT_THE_SAME;
	}
	
	public String confirmReservation() {
		reservation.setTime(date);
		reservationDAO.create(reservation);
		servicesRendered = true;
		dateSet=false;
		timeSet=false;
		
		return PAGE_THANKS;
	}
	
	
	public void handleDateSelect(SelectEvent<Date> event) {
	    date = event.getObject();
	    dateSet = true;
	    
	    //Add facesmessage
	}
	
	public void handleTimeSelect(SelectEvent<Date> event) {
	    dateTime = event.getObject();
	    timeSet = true;
	    
	    //Add facesmessage
	}
	

	// GETTERS AND SETTERS

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public boolean isServicesRendered() {
		return servicesRendered;
	}

	public void setServicesRendered(boolean servicesRendered) {
		this.servicesRendered = servicesRendered;
	}

	public Date getDate() {
		return date;
	}

	public boolean isDateSet() {
		return dateSet;
	}

	public void setDateSet(boolean dateSet) {
		this.dateSet = dateSet;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getMinDateTime() {
		return minDateTime;
	}

	public void setMinDateTime(Date minDateTime) {
		this.minDateTime = minDateTime;
	}

	public Date getMaxDateTime() {
		return maxDateTime;
	}

	public void setMaxDateTime(Date maxDateTime) {
		this.maxDateTime = maxDateTime;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	

	

	
}
