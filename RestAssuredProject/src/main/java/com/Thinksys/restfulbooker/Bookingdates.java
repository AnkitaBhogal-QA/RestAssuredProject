package com.Thinksys.restfulbooker;

public class Bookingdates {

	private String checkin;
	private String checkout;
	
	@Override
	public String toString() {
		return "Bookingdates [checkin=" + checkin + ", checkout=" + checkout + "]";
	}
	
	
	
	public Bookingdates(String checkin, String checkout) {
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public Bookingdates() {
	}


	public String getCheckin() {
		return checkin;
	}
	public String getCheckout() {
		return checkout;
	}
	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}
	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}
	
	
	
}
