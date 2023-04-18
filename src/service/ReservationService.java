package service;

import model.*;

import java.util.*;

public class ReservationService {

    private static final Map<String, IRoom> rooms = new HashMap<>();
    private static final List<Reservation> reservations= new ArrayList<>();

    private ReservationService(){
    }

    public static void addRoom(IRoom room){
        rooms.put(room.getRoomNumber(), room);
    }
    public static IRoom getARoom(String roomId){
        return rooms.get(roomId);
    }
    public static Collection<IRoom> getAllRooms(){
        return rooms.values();
    }
    public static Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }
    public static Collection<Reservation> getReservations(String customerEmail){
        List<Reservation> customerReservations = new ArrayList<>();
        for(Reservation reservation : reservations){
            if(reservation.getCustomer().getEmail().equals(customerEmail)){
                customerReservations.add(reservation);
            }
        }
        return customerReservations;
    }
    public static Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        Set<IRoom> availableRooms = new HashSet<>();
        availableRooms.addAll(rooms.values());
        for(Reservation reservation : reservations){
            if((checkInDate.compareTo(reservation.getCheckInDate()) >= 0 && checkInDate.compareTo(reservation.getCheckOutDate()) < 0) ||
            checkOutDate.compareTo(reservation.getCheckInDate()) > 0 && checkInDate.compareTo(reservation.getCheckInDate()) <= 0){
                if(availableRooms.contains(reservation.getRoom())){
                    availableRooms.remove(reservation.getRoom());
                }
            }
        }
        return availableRooms;
    }
    public static void printAllReservations(){
        for(Reservation reservation : reservations){
            System.out.println(reservation);
        }
    }

}
