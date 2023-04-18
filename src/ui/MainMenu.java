package ui;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;
import java.util.List;

import static api.HotelResource.*;


public class MainMenu {

    private boolean key;

    public MainMenu(){
        this.key = true;
    }
    public boolean getKey(){
        return key;
    }
    public void setKey(){
        key = !key;
    }

    public void chooseOption(){
        System.out.println(this);
        Scanner scanner = new Scanner(System.in);
        AdminMenu adminMenu = new AdminMenu();
        try {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("1")) {
                reserveRoom();
            }
            else if (userInput.equalsIgnoreCase("2")) {
                String email;
                System.out.println("Enter your account email: (format: name@domail.com)");
                email = scanner.nextLine();
                if(getCustomer(email) == null) {
                    System.out.println("Cannot find user account.");
                }
                else {
                    System.out.println("\n");
                    for (Reservation reservation : getReservations(email)) {
                        System.out.println(reservation);
                    }
                }
                System.out.println("Press \"Enter\" to continue...");
                scanner.nextLine();
            }
            else if (userInput.equalsIgnoreCase("3")) {
                createAccount();
            }
            else if (userInput.equalsIgnoreCase("4")) {
                while(adminMenu.getKey()){
                    adminMenu.chooseOption();
                }
            }
            else if (userInput.equalsIgnoreCase("5")) {
                System.out.println("Bye~~");
                setKey();
            }
            else {
                System.out.println("Invalid option number. Please reselect");
            }
        }
        catch (Exception ex) {
                ex.getLocalizedMessage();
        }
    }

    public void reserveRoom(){
        Calendar calendar = Calendar.getInstance();
        boolean keepRunning;
        String checkIn;
        String[] checkInSplit;
        Date checkInDate;
        String checkOut;
        String[] checkOutSplit;
        Date checkOutDate;
        Scanner scanner = new Scanner(System.in);
        do {
            keepRunning = false;
            System.out.println("Enter check-in date: mm/dd/yyyy (example:02/21/2023)");
            checkIn = scanner.nextLine();
            checkInSplit = checkIn.split("/");
            try {
                calendar.set(Integer.parseInt(checkInSplit[2]), Integer.parseInt(checkInSplit[0])-1, Integer.parseInt(checkInSplit[1]));
            } catch (Exception ex) {
                ex.getLocalizedMessage();
                System.out.println("Invalid date format");
                keepRunning = true;
            }
        }while(keepRunning);
        checkInDate = calendar.getTime();
        do {
            keepRunning = false;
            System.out.println("Enter check-out date: mm/dd/yyyy (example:02/21/2023)");
            checkOut = scanner.nextLine();
            checkOutSplit = checkOut.split("/");
            try {
                calendar.set(Integer.parseInt(checkOutSplit[2]), Integer.parseInt(checkOutSplit[0])-1, Integer.parseInt(checkOutSplit[1]));
            } catch (Exception ex) {
                ex.getLocalizedMessage();
                System.out.println("Invalid date format");
                keepRunning = true;
            }
        }while(keepRunning);
        checkOutDate = calendar.getTime();
        List<String> availableRooms = new ArrayList<>();
        if(!findARoom(checkInDate, checkOutDate).isEmpty()) {
            System.out.println("Available rooms:");
            for (IRoom availableRoom : findARoom(checkInDate, checkOutDate)) {
                System.out.println(availableRoom);
                availableRooms.add(availableRoom.getRoomNumber());
            }
        }
        else{
            do{
                calendar.setTime(checkInDate);
                calendar.add(Calendar.WEEK_OF_MONTH, 1);
                checkInDate = calendar.getTime();
                calendar.setTime(checkOutDate);
                calendar.add(Calendar.WEEK_OF_MONTH, 1);
                checkOutDate = calendar.getTime();
                if(!findARoom(checkInDate, checkOutDate).isEmpty()) {
                    System.out.println("No available rooms.");
                    System.out.println("How about rooms from [" + checkInDate + "] to [" + checkOutDate + "]?:");
                }
                for (IRoom availableRoom : findARoom(checkInDate, checkOutDate)) {
                    System.out.println(availableRoom);
                    availableRooms.add(availableRoom.getRoomNumber());
                }
            }while(findARoom(checkInDate, checkOutDate).isEmpty());
        }
        System.out.println("\nWould you like to book a room? Y/N");
        String answer;
        do {
            keepRunning = false;
            answer = scanner.nextLine();
            if(answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("yes")){
                System.out.println("Do you have a account with us? Y/N");
                do {
                    keepRunning = false;
                    String email;
                    String roomNumber = "";
                    answer = scanner.nextLine();
                    if(answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("yes")){
                        System.out.println("Enter your account email: (format: name@domail.com)");
                        email = scanner.nextLine();
                        if(getCustomer(email) == null){
                            System.out.println("Account not found. Please create an account first!");
                            System.out.println("\nPress \"Enter\" to continue...");
                            scanner.nextLine();
                            break;
                        }
                        else {
                            do {
                                System.out.println("Which room number would you like to reserve?");
                                roomNumber = scanner.nextLine();
                                if (availableRooms.contains(roomNumber)) {
                                    bookARoom(email, getRoom(roomNumber), checkInDate, checkOutDate);
                                    System.out.println("***RESERVATION COMPLETE***\n");
                                    System.out.println("Your reservations:");
                                    for(Reservation reservation : getReservations(email)){
                                        System.out.println(reservation);
                                    }
                                    System.out.println("Press \"Enter\" to continue...");
                                    scanner.nextLine();
                                }
                                else {
                                    System.out.println("Room number not available. Please choose again.");
                                    roomNumber = "";
                                }
                            }while(roomNumber.isEmpty());
                        }
                    }
                    else if(answer.equalsIgnoreCase("N") || answer.equalsIgnoreCase("no")){
                        System.out.println("Please create an account first!");
                        System.out.println("\nPress \"Enter\" to continue...");
                        scanner.nextLine();
                        keepRunning = false;
                    }
                    else{
                        System.out.println("Please enter Yes(Y) / No(N)");
                        keepRunning = true;
                    }
                }while(keepRunning);
            }
            else if(answer.equalsIgnoreCase("N") || answer.equalsIgnoreCase("no")){
                keepRunning = false;
            }
            else{
                System.out.println("Please enter Yes(Y) / No(N)");
                keepRunning = true;
            }
        }while(keepRunning);
    }
    public void createAccount(){
        String firstName;
        String lastName;
        String email;
        boolean keepRunning = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("============(New Account)============");
        System.out.println("Enter first name:");
        firstName = scanner.nextLine();
        System.out.println("Enter last name:");
        lastName = scanner.nextLine();
        do {
            System.out.println("Enter email address: (format: name@domain.com)");
            email = scanner.nextLine();
            try {
                Customer customer = new Customer(firstName, lastName, email);
                keepRunning = false;
            } catch (Exception ex) {
                System.out.println(ex.getLocalizedMessage());
                keepRunning = true;
            }
        }while(keepRunning);
        createACustomer(email, firstName, lastName);
        System.out.println("***ACCOUNT CREATED***");
        System.out.println("\nPress \"Enter\" to continue...");
        scanner.nextLine();
    }
    @Override
    public String toString(){
        return
                "\n==============(Main Menu)==============\n" +
                "1. Find and reserve a room\n" +
                "2. See my reservations\n" +
                "3. Create an account\n" +
                "4. Admin Menu\n" +
                "5. Exit\n" +
                "=======================================\n\n" +
                "Please select a number for the menu option";
    }
}
