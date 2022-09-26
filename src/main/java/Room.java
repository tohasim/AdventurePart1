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
    public void setRooms(Room north, Room south, Room east, Room west) {
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
    }
}
