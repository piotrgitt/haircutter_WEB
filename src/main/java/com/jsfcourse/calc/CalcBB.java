package com.jsfcourse.calc;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class CalcBB {
	private String x;
	private String y;
	private Double result;

	@Inject
	FacesContext ctx;
	
	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public Double getResult() {
		return result;
	}

	public String calc() {
		try {
			double x = Double.parseDouble(this.x);
			double y = Double.parseDouble(this.y);
			
			result = x + y;
			
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return "showresult"; 
		} catch (Exception e) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "B��d podczas przetwarzania parametr�w", null));
			return null; 
		}
				
	}

	public String info() {
		return "info"; 
	}
}
