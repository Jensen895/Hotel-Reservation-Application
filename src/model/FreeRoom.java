package model;

public class FreeRoom extends Room{

    public FreeRoom(String roomNumber, RoomType type){
        super(roomNumber, 0.0, type);
    }

    @Override
    public String toString(){
        return "Room Number: " + this.getRoomNumber() +
                ", " + "Room Type: " + this.getRoomType() +
                ", " + "Price: FREE";
    }
}
