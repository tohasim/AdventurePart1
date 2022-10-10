import Enums.Direction;
import Enums.ReturnMessage;

import java.util.ArrayList;

public class Player {
    private boolean awaitingUnlock = false;
    private Room roomToUnlock = null;
    private Room currentRoom;
    private ArrayList<Item> inventory;
    private int hp;
    private static int HP_MAX = 100;
    private boolean dead;
    private Weapon equippedWeapon;

    Player(Room currentRoom){
        this.currentRoom = currentRoom;
        this.currentRoom.setVisited(true);
        inventory = new ArrayList<>();
        hp = 10;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void GoDirection(Direction direction) {
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
        if (!roomToUnlock.isLightOn()){
            roomToUnlock.turnOnLight();
            GoDirection(currentRoom.getDirection(roomToUnlock));
            return true;
        }
        return false;
    }

    public boolean takeItem(String itemToTake) {
        Item item = currentRoom.findItem(itemToTake);
        if (item != null){
            inventory.add(item);
            currentRoom.removeItem(item);
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

    public void heal(int healAmount){
        if (hp + healAmount < HP_MAX )
            hp += healAmount;
        else hp = HP_MAX;
    }
    public void damage(int damageAmount){
        if (hp - damageAmount <= 0)
            dead = true;
        hp -= damageAmount;
    }

    public boolean isDead() {
        return dead;
    }

    public ReturnMessage eatItem(String itemToTake) {
        Item itemToEat = findInventoryItem(itemToTake);
        if (itemToEat == null)
            return ReturnMessage.NO_ITEM_INVENTORY;
        if (itemToEat instanceof FoodItem food){
            inventory.remove(food);
            heal(food.getHpAdd());
            return ReturnMessage.OK;
        }
        return ReturnMessage.ITEM_NOT_FOOD;
    }

    public int getHp() {
        return hp;
    }

    public ReturnMessage EquipItem(String itemToEquip) {
        Item item = findInventoryItem(itemToEquip);
        if (item == null)
            return ReturnMessage.NO_ITEM_INVENTORY;
        if (item instanceof Weapon weapon){
            equippedWeapon = weapon;
            return ReturnMessage.OK;
        }
        return ReturnMessage.ITEM_NOT_WEAPON;
    }

    public ReturnMessage attack() {
        if (equippedWeapon == null)
            return ReturnMessage.NO_WEAPON_EQUIPPED;
        if (!equippedWeapon.canUse())
            return ReturnMessage.WEAPON_OUT_OF_AMMO;
        return ReturnMessage.OK;
    }
}
