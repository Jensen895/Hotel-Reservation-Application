package model;

import java.util.Objects;

public class Room implements IRoom{
    
    private String roomNumber;
    private Double price;
    private RoomType type;

    public Room(String roomNumber, Double price, RoomType type){
        this.roomNumber = roomNumber;
        this.price = price;
        this.type = type;
    }
    
    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return type;
    }

    @Override
    public boolean isFree() {
        if(getRoomPrice() == 0.0)
            return true;
        return false;
    }
    
    @Override
    public String toString(){
        return "Room Number: " + roomNumber +
                ", " + "Room Type: " + type+
                ", " + "Price: $" + price;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomNumber.equals(room.roomNumber);
    }
    @Override
    public int hashCode(){
        return Objects.hash(roomNumber);
    }
}
