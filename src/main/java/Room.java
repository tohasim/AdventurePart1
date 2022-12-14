import Enums.Direction;
import Enums.ReturnMessage;

import java.util.ArrayList;
import java.util.Map;

public class Room {
    private Room north;
    private Room south;
    private Room east;
    private Room west;
    private String name;
    private String description;
    private boolean visited;
    private boolean locked;
    private boolean lightOn = true;
    private ArrayList<Item> items = new ArrayList<Item>();
    public ArrayList<Room> triedRooms = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();

    public Room(String name) {
        this.name = name;
    }

    public Room getRoom(Direction direction) {
        Room room = null;
        switch (direction) {
            case NORTH -> room = north;
            case SOUTH -> room = south;
            case EAST -> room = east;
            case WEST -> room = west;
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
        if (connectedRoom.north == null) {
            connectedRoom.setNorth(this);
        }
    }

    public void setEast(Room connectedRoom) {
        this.east = connectedRoom;
        if (connectedRoom.west == null) {
            connectedRoom.setWest(this);
        }
    }

    public void setWest(Room connectedRoom) {
        this.west = connectedRoom;
        if (connectedRoom.east == null) {
            connectedRoom.setEast(this);
        }
    }

    public void setNorth(Room connectedRoom) {
        this.north = connectedRoom;
        if (connectedRoom.south == null) {
            connectedRoom.setSouth(this);
        }
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Direction getDirection(Room triedRoom) {
        Direction direction = null;
        if (north != null && triedRoom.getName().equals(north.getName())) {
            direction = Direction.NORTH;
        } else if (south != null && triedRoom.getName().equals(south.getName())) {
            direction = Direction.SOUTH;
        } else if (east != null && triedRoom.getName().equals(east.getName())) {
            direction = Direction.EAST;
        } else if (west != null && triedRoom.getName().equals(west.getName())) {
            direction = Direction.WEST;
        }
        return direction;
    }

    public void unlockRoom(Room roomToUnlock) {
        roomToUnlock.locked = false;
    }

    public void lockRoom() {
        this.locked = true;
    }

    public boolean isLightOn() {
        return lightOn;
    }

    public void turnOnLight() {
        lightOn = true;
    }

    public void turnOffLight() {
        lightOn = false;
    }

    public boolean isLocked() {
        return locked;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void addItems(Item item) {
        items.add(item);
    }

    public void addItems(ArrayList<Item> items) {
        this.items.addAll(items);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public Item findItem(String itemToTake) {
        for (Item item : getItems()) {
            if (item.toString().toLowerCase().contains(itemToTake)) {
                return item;
            }
        }
        return null;
    }

    public void addEnemy(Enemy enemyToAdd){
        enemies.add(enemyToAdd);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public Enemy findEnemy(String name) {
        for (Enemy enemy : enemies) {
            if (enemy.getName().trim().equalsIgnoreCase(name.trim()))
                return enemy;
        }
        return null;
    }
    public void removeEnemy(Enemy enemyToRemove){
        enemies.remove(enemyToRemove);
    }
    public void dropEnemyItem(Item enemyItemToDrop){
        items.remove(enemyItemToDrop);

    }
    public void addEnemyItem(Item enemyItemToAdd){
        items.add(enemyItemToAdd);
    }
}