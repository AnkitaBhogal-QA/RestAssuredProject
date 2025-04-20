package com.Thinksys.restfulbooker;

public class Bookingid {

	private int bookingid;
	private Booking booking;
	
	@Override
	public String toString() {
		return "BookingId [bookingId=" + bookingid + ", booking=" + booking + "]";
	}
	
	public Bookingid() {
	
	}
	public Bookingid(int bookingId, Booking booking) {
		this.bookingid = bookingId;
		this.booking = booking;
	}

	public int getBookingid() {
		return bookingid;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBookingid(int bookingid) {
		this.bookingid = bookingid;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	

	
}
