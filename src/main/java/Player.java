public class Player {
    private Room currentRoom;
    private UserInterface UI;
    Player(Room currentRoom, UserInterface UI){
        this.UI = UI;
        this.currentRoom = currentRoom;
        this.currentRoom.setVisited(true);
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void GoDirection(String direction) {
        if (currentRoom.getRoom(direction) != null){
            currentRoom = currentRoom.getRoom(direction);
            if (currentRoom.isVisited()){
                UI.enterRoom(currentRoom, direction);
            } else{
                UI.enterNewRoom(currentRoom, direction);
                currentRoom.setVisited(true);
            }
        }else{
            System.out.println("You cannot go this way");
        }
    }
}
