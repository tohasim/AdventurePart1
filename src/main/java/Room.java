import java.util.ArrayList;

public class Room{
    private Room north;
    private Room south;
    private Room east;
    private Room west;
    private String name;
    private String description;
    private boolean visited;
    private boolean locked;
    private boolean lightOn;

    public ArrayList<Room> triedRooms;

    public Room(String name) {
        this.name = name;
        triedRooms = new ArrayList<>();
        lightOn  = true;
    }

    public Room getRoom(String direction) {
        Room room = null;
        switch (direction.toLowerCase()){
            case "north" -> room = north;
            case "south" -> room = south;
            case "east" -> room = east;
            case "west" -> room = west;
        }
        return room;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSouth(Room connectedRoom) {
        this.south = connectedRoom;
        if (connectedRoom.north == null){
            connectedRoom.setNorth(this);
        }
    }

    public void setEast(Room connectedRoom) {
        this.east = connectedRoom;
        if (connectedRoom.west == null){
            connectedRoom.setWest(this);
        }
    }

    public void setWest(Room connectedRoom) {
        this.west = connectedRoom;
        if (connectedRoom.east == null){
            connectedRoom.setEast(this);
        }
    }

    public void setNorth(Room connectedRoom) {
        this.north = connectedRoom;
        if (connectedRoom.south == null){
            connectedRoom.setSouth(this);
        }
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public String getDirection(Room triedRoom) {
        String direction = "";
        if (north != null && triedRoom.getName().equals(north.getName())){
            direction = "north";
        } else if (south != null && triedRoom.getName().equals(south.getName())){
            direction = "south";
        } else if (east != null && triedRoom.getName().equals(east.getName())){
            direction = "east";
        } else if (west != null && triedRoom.getName().equals(west.getName())){
            direction = "west";
        }
        return direction;
    }

    public void unlockRoom(Room roomToUnlock){
        roomToUnlock.locked = false;
    }

    public void lockRoom() {
        this.locked = true;
    }

    public boolean isLightOn() {
        return lightOn;
    }

    public void turnOnLight(){
        lightOn = true;
    }

    public void turnOffLight(){
        lightOn = false;
    }

    public boolean isLocked() {
        return locked;
    }
}
