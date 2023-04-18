package model;

import service.CustomerService;

import java.util.Date;
import java.util.Objects;

public class Reservation {

    private Customer customer;
    private IRoom room;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Date getCheckInDate(){
        return checkInDate;
    }

    public Date getCheckOutDate(){
        return checkOutDate;
    }

    public IRoom getRoom(){
        return room;
    }
    public Customer getCustomer(){
        return customer;
    }

    @Override
    public String toString(){
        return customer + "\n" +
                room + "\n" +
                "Check In Date: " + checkInDate + "\n" +
                "Check Out Date: " + checkOutDate + "\n";
    }
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Reservation reservation = (Reservation) o;
        return reservation.equals(reservation.checkInDate);
    }
    @Override
    public int hashCode(){
        return Objects.hash(checkInDate);
    }
}
