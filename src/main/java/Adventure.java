import java.util.ArrayList;

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
        shouldRun = true;
        String directionToGo = "";
        ReturnMessage returnMessage = null;
        while (shouldRun) {
            returnMessage = ReturnMessage.OK;
            ArrayList<String> userChoice = UI.PromptUserChoice();
            /*String interactItem = null;
            if (userChoice[0].contains("take")) {
                interactItem = userChoice.substring(5);
                userChoice = "take";
            } else if (userChoice.contains("place")) {
                interactItem = userChoice.substring(6);
                userChoice = "place";
            }*/
            switch (userChoice.get(0)) {
                case "go" -> {
                    switch (userChoice.get(1)) {
                        case "north", "n" -> {directionToGo = "north";
                            returnMessage = checkAndGoDirection(directionToGo);
                        }
                        case "south", "s" -> {
                            directionToGo = "south";
                            returnMessage = checkAndGoDirection(directionToGo);
                        }
                        case "east", "e" -> {
                            directionToGo = "east";
                            returnMessage = checkAndGoDirection(directionToGo);
                        }
                        case "west", "w" -> {
                            directionToGo = "west";
                            returnMessage = checkAndGoDirection(directionToGo);
                        }
                        default -> UI.printMessage(ReturnMessage.NOT_A_DIRECTION);
                    }
                }
                case "north", "n" -> {directionToGo = "north";
                    returnMessage = checkAndGoDirection(directionToGo);
                }
                case "south", "s" -> {
                    directionToGo = "south";
                    returnMessage = checkAndGoDirection(directionToGo);
                }
                case "east", "e" -> {
                    directionToGo = "east";
                    returnMessage = checkAndGoDirection(directionToGo);
                }
                case "west", "w" -> {
                    directionToGo = "west";
                    returnMessage = checkAndGoDirection(directionToGo);
                }
                case "look" -> UI.PrintDescription(player.getCurrentRoom());
                case "help" -> UI.Help();
                case "exit" -> EndGame();
                case "unlock" -> returnMessage = UnlockNearby(directionToGo);
                case "turn"-> {
                    if (String.join(" " ,userChoice).equals("turn on light"))
                        TurnOnLight();
                    else returnMessage = ReturnMessage.UNKNOWN_COMMAND;
                }
                case "take" -> {
                    userChoice.remove(0);
                    String itemToTake = String.join(" ", userChoice);
                    returnMessage = takeItem(itemToTake);
                }
                case "place" -> {
                    userChoice.remove(0);
                    String itemToTake = String.join(" ", userChoice);
                    returnMessage = placeItem(itemToTake);
                }
                case "eat" ->  {
                    userChoice.remove(0);
                    String itemToTake = String.join(" ", userChoice);
                    returnMessage = eatItem(itemToTake);
                }
                case "inventory" -> UI.printInventory(player.getInventory());
                case "health" -> UI.printHealth(player.getHp());
                case "equip" -> {
                    userChoice.remove(0);
                    String itemToEquip = String.join(" ", userChoice);
                    returnMessage = equipItem(itemToEquip);
                }
                case "attack" -> returnMessage = attack();
                default -> returnMessage = ReturnMessage.UNKNOWN_COMMAND;
            }
            UI.printMessage(returnMessage);
            if (player.getCurrentRoom().getName().equals("Ninth room") || player.getCurrentRoom().getName().equals("GOAAAAAL"))
                GameOver();
        }
        UI.printMessage(returnMessage);
    }

    private ReturnMessage attack() {
        ReturnMessage returnMessage = player.attack();
        if(returnMessage == ReturnMessage.OK){
            UI.attack();
        }
        return returnMessage;
    }

    private ReturnMessage equipItem(String itemToEquip) {
        ReturnMessage returnMessage = player.EquipItem(itemToEquip);
        if (returnMessage == ReturnMessage.OK){
            UI.EquipItem(itemToEquip);
        }
        return returnMessage;
    }

    private ReturnMessage UnlockNearby(String directionToGo) {
        if(!player.Unlock()) return ReturnMessage.NO_LOCKED_ROOMS;
        checkAndGoDirection(directionToGo);
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
                    //TODO: At the moment it is possible to ignore this and pass through dark room
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
        if(!player.placeItem(interactItem) || interactItem == ""){
            return  ReturnMessage.NO_ITEM_INVENTORY;
        }
        UI.placeItem(player.getCurrentRoom().findItem(interactItem));
        return ReturnMessage.OK;
    }

    private ReturnMessage takeItem(String interactItem) {
        if (!player.takeItem(interactItem) || interactItem == "")
            return ReturnMessage.NO_ITEM_ROOM;
        UI.takeItem(player.findInventoryItem(interactItem));
        return ReturnMessage.OK;
    }
    private ReturnMessage eatItem(String itemToTake) {
        ReturnMessage returnMessage = player.eatItem(itemToTake);
        if (returnMessage == ReturnMessage.OK){
            UI.eatItem(itemToTake);
        }
        return returnMessage;
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