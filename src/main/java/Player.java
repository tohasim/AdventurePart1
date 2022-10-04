import java.util.ArrayList;

public class Player {
    private boolean awaitingUnlock = false;
    private Room roomToUnlock = null;
    private Room currentRoom;
    private ArrayList<Item> inventory;
    Player(Room currentRoom){
        this.currentRoom = currentRoom;
        this.currentRoom.setVisited(true);
        inventory = new ArrayList<>();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void GoDirection(String direction) {
        Room roomToVisit = currentRoom.getRoom(direction);
        if (!roomToVisit.triedRooms.contains(currentRoom))
            roomToVisit.triedRooms.add(currentRoom);
        currentRoom = currentRoom.getRoom(direction);
        awaitingUnlock = false;
    }

    public boolean Unlock() {
        if (awaitingUnlock){
            currentRoom.unlockRoom(roomToUnlock);
            awaitingUnlock = false;
            roomToUnlock = null;
            return true;
        }else{
            return false;
        }
    }

    public boolean TurnOnLight() {
        if (!currentRoom.isLightOn()){
            currentRoom.turnOnLight();
            return true;
        }
        return false;
    }

    public boolean takeItem(String itemToTake) {
        Item item = currentRoom.findItem(itemToTake);
        if (item != null){
            inventory.add(item);
            return true;
        }
        return false;
    }
    public boolean placeItem(String itemToPlace){
        for (Item item : inventory){
            if (item.toString().contains(itemToPlace)){
                currentRoom.addItems(item);
                inventory.remove(item);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public Item findInventoryItem(String itemToFind){
        for (Item item : inventory) {
            if (item.toString().contains(itemToFind))
                return item;
        }
        return null;
    }

    public void setRoomToUnlock(Room roomToUnlock) {
        this.roomToUnlock = roomToUnlock;
    }

    public void setAwaitingUnlock(boolean b) {
        awaitingUnlock = b;
    }
}
