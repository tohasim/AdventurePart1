public class Adventure {
    private boolean shouldRun;
    private UserInterface UI;
    private Map creator;
    private Player player;
    public void StartAdventure(){
        UI = new UserInterface();
        creator = new Map();
        player = new Player(creator.getRoomOne());

        UI.Welcome();
        UI.ExplainGame();
        UI.Help();
        MainLoop();
    }
    void MainLoop() {
        ReturnMessage returnMessage = ReturnMessage.OK;
        shouldRun = true;
        while(shouldRun){
            String userChoice = UI.PromptUserChoice();
            String interactItem = null;
            if (userChoice.contains("take")){
                interactItem = userChoice.substring(5);
                userChoice = "take";
            } else if (userChoice.contains("place")) {
                interactItem = userChoice.substring(6);
                userChoice = "place";
            }
            switch (userChoice.toLowerCase()){
                case "go north", "north", "n" -> {
                    returnMessage = checkAndGoDirection("north");
                }
                case "go south", "south", "s" -> {
                    returnMessage = checkAndGoDirection("south");
                }
                case "go east", "east", "e" -> {
                    returnMessage = checkAndGoDirection("east");
                }
                case "go west", "west", "w" -> {
                    returnMessage = checkAndGoDirection("west");
                }
                case "look" -> UI.PrintDescription(player.getCurrentRoom());
                case "help" -> UI.Help();
                case "exit" -> EndGame();
                case "unlock" -> UnlockNearby();
                case "turn on light" -> TurnOnLight();
                case "take" ->  takeItem(interactItem);
                case "place" ->  placeItem(interactItem);
                case "inventory" -> UI.printInventory(player.getInventory());
                default -> {
                    returnMessage = ReturnMessage.UNKNOWN_COMMAND;
                }
            }
            UI.printMessage(returnMessage);
            if (player.getCurrentRoom().getName().equals("Ninth room") || player.getCurrentRoom().getName().equals("GOAAAAAL"))
                GameOver();
        }
         UI.printMessage(returnMessage);
    }

    private ReturnMessage UnlockNearby() {
        if(!player.Unlock()) return ReturnMessage.NO_LOCKED_ROOMS;
        return ReturnMessage.OK;
    }

    private ReturnMessage checkAndGoDirection(String direction) {
        ReturnMessage returnMessage = ReturnMessage.OK;
        Room roomToVisit = player.getCurrentRoom().getRoom(direction);
        if (roomToVisit != null){
            if (roomToVisit.isLocked()){
                returnMessage = ReturnMessage.ROOM_LOCKED;
                player.setRoomToUnlock(roomToVisit);
                player.setAwaitingUnlock(true);
            }else {
                if (roomToVisit.isLightOn()){
                    if (roomToVisit.isVisited()) {
                        UI.enterRoom(roomToVisit, direction);
                    } else {
                        UI.enterNewRoom(roomToVisit, direction);
                        roomToVisit.setVisited(true);
                    }
                }else {
                    returnMessage = ReturnMessage.ROOM_DARK;
                }
                player.GoDirection(direction);
            }
        }else{
            returnMessage = ReturnMessage.NO_ROOM_THIS_DIRECTION;
        }
        return returnMessage;
    }

    private void TurnOnLight() {
        if (player.TurnOnLight()){
            UI.turnOnLight(player.getCurrentRoom());

        }
    }

    private ReturnMessage placeItem(String interactItem) {
        if(!player.placeItem(interactItem)){
            return  ReturnMessage.NO_ITEM_INVENTORY;
        }
        return ReturnMessage.OK;
    }

    private ReturnMessage takeItem(String interactItem) {
        if (!player.takeItem(interactItem))
            return ReturnMessage.NO_ITEM_ROOM;
        return ReturnMessage.OK;
    }

    private void GameOver() {
        if (UI.TryAgain()){
            StartAdventure();
        }else{
            EndGame();
        }
    }

    private void EndGame() {
        UI.ExitMessage();
        shouldRun = false;
    }
}