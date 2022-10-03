import java.util.ArrayList;

public class Player {
    private boolean awaitingUnlock = false;
    private Room roomToUnlock = null;
    private Room currentRoom;
    private ArrayList<Item> inventory;
//    private final UserInterface UI;
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
        if (roomToVisit != null){
            if (roomToVisit.isLocked()){
                System.out.println("Room is locked");
                roomToUnlock = roomToVisit;
                awaitingUnlock = true;
            }else {
                if (roomToVisit.isLightOn()){
                    if (roomToVisit.isVisited()) {
                        UI.enterRoom(roomToVisit, direction);
                    } else {
                        UI.enterNewRoom(roomToVisit, direction);
                        roomToVisit.setVisited(true);
                    }
                }else {
                    System.out.println("The room you entered is dark, go back, or turn on the light");
                }
                if (!roomToVisit.triedRooms.contains(currentRoom))
                    roomToVisit.triedRooms.add(currentRoom);
                currentRoom = currentRoom.getRoom(direction);
                awaitingUnlock = false;
            }
        }else{
            System.out.println("You cannot go this way");
        }
    }

    public void Unlock() {
        if (awaitingUnlock){
            currentRoom.unlockRoom(roomToUnlock);
            GoDirection(currentRoom.getDirection(roomToUnlock));
            awaitingUnlock = false;
            roomToUnlock = null;
        }else{
            System.out.println("No locked rooms nearby");
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
}
