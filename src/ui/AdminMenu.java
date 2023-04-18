package ui;

import api.AdminResource;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static api.AdminResource.*;

public class AdminMenu {

    private boolean key;

    public AdminMenu(){
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
        try {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("1")) {
                for(Customer customer : getAllCustomers()){
                    System.out.println(customer);
                }
                System.out.println("\nPress \"Enter\" to continue...");
                scanner.nextLine();
            }
            else if (userInput.equalsIgnoreCase("2")) {
                for(IRoom room : getAllRooms()){
                    System.out.println(room);
                }
                System.out.println("\nPress \"Enter\" to continue...");
                scanner.nextLine();
            }
            else if (userInput.equalsIgnoreCase("3")) {
                displayAllReservations();
                System.out.println("Press \"Enter\" to continue...");
                scanner.nextLine();
            }
            else if (userInput.equalsIgnoreCase("4")) {
                addRoom();
            }
            else if (userInput.equalsIgnoreCase("5")) {
                this.setKey();
            }
            else {
                System.out.println("Invalid option number. Please reselect");
            }
           }
        catch (Exception ex) {
            ex.getLocalizedMessage();
        }
    }

    private void addRoom(){
        boolean keepRunning = true;
        Scanner scanner  = new Scanner(System.in);
        List<IRoom> rooms = new ArrayList<IRoom>();
        while(keepRunning) {
            String roomNumber = "";
            Double price = -1.0;
            RoomType roomType = null;
            String answer = "";
            System.out.println("============(New Room)============");
            do {
                System.out.println("Enter room number");
                roomNumber = scanner.nextLine();
            } while (roomNumber.isEmpty());
            do {
                System.out.println("Enter price per night");
                scanner = new Scanner(System.in);
                if (scanner.hasNextDouble())
                    price = scanner.nextDouble();
                else
                    System.out.println("Invalid price. Type again");
            } while (price < 0);
            do {
                System.out.println("Enter room type: 1 for single bed, 2 for double bed");
                scanner = new Scanner(System.in);
                answer = scanner.nextLine();
                if (answer.length() == 1) {
                    switch (answer.charAt(0)) {
                        case '1':
                            roomType = RoomType.SINGLE;
                            break;
                        case '2':
                            roomType = RoomType.DOUBLE;
                            break;
                        default:
                            System.out.println("Invalid option. Please choose again.");
                            answer = "";
                    }
                } else {
                    System.out.println("Error: Invalid action");
                }
            } while (answer.length() != 1);
            IRoom room;
            if(price == 0.0) {
                room = new FreeRoom(roomNumber, roomType);
            }
            else{
                room = new Room(roomNumber, price, roomType);
            }
            rooms.add(room);
            System.out.println("Do you want to add another room? Y / N");
            do {
                answer = scanner.nextLine();
                if(answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("yes")){
                    keepRunning = true;
                }
                else if(answer.equalsIgnoreCase("N") || answer.equalsIgnoreCase("no")){
                    keepRunning = false;
                }
                else{
                    System.out.println("Please enter Yes(Y) / No(N)");
                    answer = "";
                }
            }while(answer.length() < 1 || answer.length() > 3);
        }
        AdminResource.addRooms(rooms);
    }
    @Override
    public String toString(){
        return
                "\n**************(Admin Menu)**************\n" +
                "1. See all customers\n" +
                "2. See all rooms\n" +
                "3. See all reservations\n" +
                "4. Add a room\n" +
                "5. Back to Main Menu\n" +
                "****************************************\n\n"+
                "Please select a number for the menu option";
    }
}
