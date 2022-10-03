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
                case "go north", "north", "n" -> checkAndGoDirection("north");
                case "go south", "south", "s" -> player.GoDirection("south");
                case "go east", "east", "e" -> player.GoDirection("east");
                case "go west", "west", "w" -> player.GoDirection("west");
                case "look" -> UI.PrintDescription(player.getCurrentRoom());
                case "help" -> UI.Help();
                case "exit" -> EndGame();
                case "unlock" -> UnlockNearby();
                case "turn on light" -> TurnOnLight();
                case "take" ->  takeItem(interactItem);
                case "place" ->  placeItem(interactItem);
                case "inventory" -> UI.printInventory(player.getInventory());
                default -> UI.printMessage("Unknown command, type \"help\" for a list of commands");
            }
            if (player.getCurrentRoom().getName().equals("Ninth room") || player.getCurrentRoom().getName().equals("GOAAAAAL"))
                GameOver();
        }
    }

    private void UnlockNearby() {
        if(!player.Unlock()) UI.printMessage("No locked rooms nearby");
    }

    private void checkAndGoDirection(String direction) {
        Room roomToVisit = player.getCurrentRoom().getRoom(direction);
        if (roomToVisit != null){
            if (roomToVisit.isLocked()){
                UI.printMessage("Room is locked");
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
                    UI.printMessage("The room you entered is dark, go back, or turn on the light");
                }
                player.GoDirection(direction);
            }
        }else{
            UI.printMessage("You cannot go this way");
        }
    }

    private void TurnOnLight() {
        if (player.TurnOnLight()){
            UI.turnOnLight(player.getCurrentRoom());

        }
    }

    private void placeItem(String interactItem) {
        if(!player.placeItem(interactItem)){
            UI.printMessage("No item like that was found in your inventory");
        }
    }

    private void takeItem(String interactItem) {
        if (!player.takeItem(interactItem))
            UI.printMessage("No item like that was found in the room");
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