package com.jsfcourse.haircutter;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import jsf.haircutter.dao.ReservationDAO;
import jsf.haircutter.dao.ServiceDAO;
import jsf.haircutter.entities.Service;
import jsf.haircutter.entities.User;
import jsf.haircutter.entities.Reservation;

@Named
@RequestScoped
public class ReservationBB  {

	private static final String PAGE_THANKS = "/public/thanks?faces-redirect=true";
	private static final String PAGE_CHOOSE_TIME = "/pages/userPages/temp_reservation?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private boolean servicesRendered  =  true;
	
	//SETTING CALENDAR
	private LocalDate date;
	private LocalDate date2;
	 private LocalDate minDateTime;
	 private LocalDate maxDateTime;
	private boolean dateSet = false;
   
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
	
	
	public void handleDateSelect(SelectEvent<LocalDate> event) {
		System.out.println(dateSet);
	    date = event.getObject();
	    dateSet = true;
	    
	    //Add facesmessage
	}
	//PRIME FACES CALENDAR SETTINGS
	

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

	public LocalDate getDate() {
		return date;
	}

	public boolean isDateSet() {
		return dateSet;
	}

	public void setDateSet(boolean dateSet) {
		this.dateSet = dateSet;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalDate getDate2() {
		return date2;
	}

	public void setDate2(LocalDate date2) {
		this.date2 = date2;
	}

	public LocalDate getMinDateTime() {
		return minDateTime;
	}

	public void setMinDateTime(LocalDate minDateTime) {
		this.minDateTime = minDateTime;
	}

	public LocalDate getMaxDateTime() {
		return maxDateTime;
	}

	public void setMaxDateTime(LocalDate maxDateTime) {
		this.maxDateTime = maxDateTime;
	}

	

	

	
}
