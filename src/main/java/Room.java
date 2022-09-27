public class Room{
    private Room north;
    private Room south;
    private Room east;
    private Room west;
    private String name;
    private String description;

    public Room(String name) {
        this.name = name;
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
}
