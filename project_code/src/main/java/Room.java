/*
The Room class holds several attribute: roomId, roomName, roomBuilding, floor
It is used in Course class
The getter methods in Room class help to get room information easily in a course instance and in student's schedule
 */

public class Room {
    private int roomId;
    private String roomName;
    private Building roomBuilding;
    private int floor;

    public Room(int roomId, String roomName, Building roomBuilding, int floor) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomBuilding = roomBuilding;
        this.floor = floor;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public Building getRoomBuilding() {
        return roomBuilding;
    }

    public int getFloor() {
        return floor;
    }
}
