package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {

    private HotelResource(){
    }

    public static Customer getCustomer(String email){
        return CustomerService.getCustomer(email);
    }
    public static void createACustomer(String email, String firstName, String lastName){
        CustomerService.addCustomer(email, firstName, lastName);
    }
    public static IRoom getRoom(String roomNumber){
        return ReservationService.getARoom(roomNumber);
    }
    public static Reservation bookARoom(String email, IRoom room, Date checkInDate, Date checkOutDate){
        return ReservationService.reserveARoom(getCustomer(email), room, checkInDate, checkOutDate);
    }
    public static Collection<Reservation> getReservations(String email){
        return ReservationService.getReservations(email);
    }
    public static Collection<IRoom> findARoom(Date checkIn, Date checkOut){
        return ReservationService.findRooms(checkIn, checkOut);
    }
}
