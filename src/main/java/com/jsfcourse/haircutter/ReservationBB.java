package com.jsfcourse.haircutter;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.simplesecurity.RemoteClient;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import java.text.SimpleDateFormat;

import jsf.haircutter.dao.ReservationDAO;
import jsf.haircutter.dao.ServiceDAO;
import jsf.haircutter.entities.Service;
import jsf.haircutter.entities.User;
import jsf.haircutter.entities.Reservation;

@Named
@RequestScoped
public class ReservationBB implements Serializable {

	private static final String PAGE_THANKS = "/public/thanks?faces-redirect=true";
	private static final String PAGE_CHOOSE_TIME = "/pages/userPages/temp_reservation?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private boolean servicesRendered  =  true;
	
	//SETTING CALENDAR
	private Date date;
	private List<Date> invalidDates;
	 private Date dateTimeDe;
    private Date dateTimeMillis;
    private List<Date> multi;
    private List<Date> range;
    private List<Integer> invalidDays;
    private Date minDate;
    private Date maxDate;
    private Date minTime;
    private Date maxTime;
    private Date minDateTime;
    private Date maxDateTime;
    private Date dateDe;
	
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
		

		// 1. Pass object through session
		// HttpSession session = (HttpSession) extcontext.getSession(true);
		// session.setAttribute("person", person);

		// 2. Pass object through flash
		flash.put("reservation", reservation);

		return PAGE_STAY_AT_THE_SAME;
	}
	//PRIME FACES CALENDAR SETTINGS
	 @PostConstruct
	    public void init() {
	        invalidDates = new ArrayList<>();
	        Date today = new Date();
	        invalidDates.add(today);
	        long oneDay = 24 * 60 * 60 * 1000;
	        for (int i = 0; i < 5; i++) {
	            invalidDates.add(new Date(invalidDates.get(i).getTime() + oneDay));
	        }

	        invalidDays = new ArrayList<>();
	        invalidDays.add(0);
	        /* the first day of week is disabled */
	        invalidDays.add(3);

	        minDate = new Date(today.getTime() - (365 * oneDay));
	        maxDate = new Date(today.getTime() + (365 * oneDay));

	        Calendar tmp = Calendar.getInstance();
	        tmp.set(Calendar.HOUR_OF_DAY, 9);
	        tmp.set(Calendar.MINUTE, 0);
	        tmp.set(Calendar.SECOND, 0);
	        tmp.set(Calendar.MILLISECOND, 0);
	        minTime = tmp.getTime();

	        tmp = Calendar.getInstance();
	        tmp.set(Calendar.HOUR_OF_DAY, 17);
	        tmp.set(Calendar.MINUTE, 0);
	        tmp.set(Calendar.SECOND, 0);
	        tmp.set(Calendar.MILLISECOND, 0);
	        maxTime = tmp.getTime();

	        minDateTime = new Date(today.getTime() - (7 * oneDay));
	        maxDateTime = new Date(today.getTime() + (7 * oneDay));

	        dateDe = GregorianCalendar.getInstance().getTime();
	        dateTimeDe = GregorianCalendar.getInstance().getTime();
	        dateTimeMillis = GregorianCalendar.getInstance().getTime();
	    }

	    public void onDateSelect(SelectEvent<Date> event) {
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
	    }

	    public void click() {
	        PrimeFaces.current().ajax().update("form:display");
	        PrimeFaces.current().executeScript("PF('dlg').show()");
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

    public void setDate(Date date) {
        this.date = date;
    }

	public List<Date> getInvalidDates() {
		return invalidDates;
	}

	public void setInvalidDates(List<Date> invalidDates) {
		this.invalidDates = invalidDates;
	}

	public Date getDateTimeDe() {
		return dateTimeDe;
	}

	public void setDateTimeDe(Date dateTimeDe) {
		this.dateTimeDe = dateTimeDe;
	}

	public Date getDateTimeMillis() {
		return dateTimeMillis;
	}

	public void setDateTimeMillis(Date dateTimeMillis) {
		this.dateTimeMillis = dateTimeMillis;
	}

	public List<Date> getMulti() {
		return multi;
	}

	public void setMulti(List<Date> multi) {
		this.multi = multi;
	}

	public List<Date> getRange() {
		return range;
	}

	public void setRange(List<Date> range) {
		this.range = range;
	}

	public List<Integer> getInvalidDays() {
		return invalidDays;
	}

	public void setInvalidDays(List<Integer> invalidDays) {
		this.invalidDays = invalidDays;
	}

	public Date getMinDate() {
		return minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

	public Date getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}

	public Date getMinTime() {
		return minTime;
	}

	public void setMinTime(Date minTime) {
		this.minTime = minTime;
	}

	public Date getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(Date maxTime) {
		this.maxTime = maxTime;
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

	public Date getDateDe() {
		return dateDe;
	}

	public void setDateDe(Date dateDe) {
		this.dateDe = dateDe;
	}
}
