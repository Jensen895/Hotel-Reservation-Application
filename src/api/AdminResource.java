package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import service.ReservationService;

public class AdminResource {

    private AdminResource(){
    }

    public static Customer getCustomer(String email){
        return CustomerService.getCustomer(email);
    }
    public static void addRooms(List<IRoom> rooms){
        for(IRoom room : rooms){
            ReservationService.addRoom(room);
        }
    }
    public static Collection<IRoom> getAllRooms(){
        return ReservationService.getAllRooms();
    }
    public static Collection<Customer> getAllCustomers(){
        return CustomerService.getAllCustomers();
    }
    public static void displayAllReservations(){
        ReservationService.printAllReservations();
    }
}
