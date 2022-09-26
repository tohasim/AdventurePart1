public class Player {
    private Room currentRoom;
    private UserInterface UI;
    Player(Room currentRoom, UserInterface UI){
        this.UI = UI;
        this.currentRoom = currentRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void GoDirection(String direction) {
        if (currentRoom.getRoom(direction) != null){
            currentRoom = currentRoom.getRoom(direction);
            UI.enterRoom(currentRoom, direction);
        }else{
            System.out.println("You cannot go this way");
        }
    }
}
