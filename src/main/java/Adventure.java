import Enums.Direction;
import Enums.ReturnMessage;

import java.util.ArrayList;

public class Adventure {
    private boolean shouldRun;
    private UserInterface UI;
    private Map creator;
    private Player player;
    public void startAdventure(){
        UI = new UserInterface();
        creator = new Map();
        player = new Player("Player", 100, creator.getRoomOne());

        UI.welcome();
        UI.explainGame();
        UI.help();
        mainLoop();
    }
    void mainLoop() {
        shouldRun = true;
        Direction directionToGo = null;
        ReturnMessage returnMessage = null;
        while (shouldRun) {
            returnMessage = ReturnMessage.OK;
            ArrayList<String> userChoice = UI.promptUserChoice();
            switch (userChoice.get(0)) {
                case "go" -> {
                    switch (userChoice.get(1)) {
                        case "north", "n" -> {directionToGo = Direction.NORTH;
                            returnMessage = checkAndGoDirection(directionToGo);
                        }
                        case "south", "s" -> {
                            directionToGo = Direction.SOUTH;
                            returnMessage = checkAndGoDirection(directionToGo);
                        }
                        case "east", "e" -> {
                            directionToGo = Direction.EAST;
                            returnMessage = checkAndGoDirection(directionToGo);
                        }
                        case "west", "w" -> {
                            directionToGo = Direction.WEST;
                            returnMessage = checkAndGoDirection(directionToGo);
                        }
                        default -> UI.printMessage(ReturnMessage.NOT_A_DIRECTION);
                    }
                }
                case "north", "n" -> {directionToGo = Direction.NORTH;
                    returnMessage = checkAndGoDirection(directionToGo);
                }
                case "south", "s" -> {
                    directionToGo = Direction.SOUTH;
                    returnMessage = checkAndGoDirection(directionToGo);
                }
                case "east", "e" -> {
                    directionToGo = Direction.EAST;
                    returnMessage = checkAndGoDirection(directionToGo);
                }
                case "west", "w" -> {
                    directionToGo = Direction.WEST;
                    returnMessage = checkAndGoDirection(directionToGo);
                }
                case "look" -> UI.printDescription(player.getCurrentRoom());
                case "help" -> UI.help();
                case "exit" -> endGame();
                case "unlock" -> returnMessage = unlockNearby(directionToGo);
                case "turn"-> {
                    if (String.join(" " ,userChoice).equals("turn on light"))
                        turnOnLight();
                    else returnMessage = ReturnMessage.UNKNOWN_COMMAND;
                }
                case "take" -> {
                    userChoice.remove(0);
                    String itemToTake = String.join(" ", userChoice).toLowerCase();
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
                case "player" -> {
                    if (String.join(" " ,userChoice).equals("player status"))
                        UI.printPlayerStatus(player);}
                case "attack" -> {
                    if (userChoice.size() == 2)
                        returnMessage = attack(userChoice.get(1));
                    else
                        returnMessage = attack("");
                }
                default -> returnMessage = ReturnMessage.UNKNOWN_COMMAND;
            }
            UI.printMessage(returnMessage);
            if (player.getCurrentRoom().getName().equals("Ninth room") || player.getCurrentRoom().getName().equals("GOAAAAAL"))
                gameOver();
        }
        UI.printMessage(returnMessage);
    }

    private ReturnMessage attack(String name) {
        ReturnMessage returnMessage;
        if (player.equippedWeapon == null)
            return ReturnMessage.NO_WEAPON_EQUIPPED;
        if (!player.equippedWeapon.canUse())
            return ReturnMessage.WEAPON_OUT_OF_AMMO;
        Enemy enemyToAttack;
        if (name.equals("")){
            if (!player.getCurrentRoom().getEnemies().isEmpty())
                enemyToAttack = player.getCurrentRoom().getEnemies().get(0);
            else enemyToAttack = null;
        }
        else{
            if (player.getCurrentRoom().getEnemies().isEmpty()){
                UI.printMessage(player.attackRock());
                return ReturnMessage.NO_ENEMY_IN_ROOM;
            }
            else enemyToAttack = player.getCurrentRoom().findEnemy(name);
        }
        if (enemyToAttack == null)
            returnMessage = ReturnMessage.NO_ENEMY_WITH_MATCHING_NAME;
        else {
            UI.printMessage(player.attackRock());
            returnMessage = attackSequence(enemyToAttack);
        }
        return returnMessage;
    }
    public ReturnMessage attackSequence(Enemy enemyToAttack) {
        ReturnMessage returnMessage = ReturnMessage.OK;
        int playerDmgDealt = player.attackEnemy(enemyToAttack);
        if (enemyToAttack.isDead()){
            UI.attack(player, enemyToAttack, playerDmgDealt, true);
            player.getCurrentRoom().removeEnemy(enemyToAttack);
            player.getCurrentRoom().dropEnemyItem(enemyToAttack.equippedWeapon);
            player.getCurrentRoom().addEnemyItem(enemyToAttack.equippedWeapon);
        }else{
            UI.attack(player, enemyToAttack, playerDmgDealt, false);
            int enemyDmgDealt = enemyToAttack.attackEnemy(player);
            if (player.isDead()){
                UI.attack(enemyToAttack, player, enemyDmgDealt, true);
                gameOver();
            }
            else
                UI.attack(enemyToAttack, player, enemyDmgDealt, false);
        }
        return returnMessage;
    }

    private ReturnMessage equipItem(String itemToEquip) {
        ReturnMessage returnMessage = player.equipItem(itemToEquip);
        if (returnMessage == ReturnMessage.OK){
            UI.equipItem(player.equippedWeapon.getName());
        }
        return returnMessage;
    }

    private ReturnMessage unlockNearby(Direction directionToGo) {
        if(!player.unlock()) return ReturnMessage.NO_LOCKED_ROOMS;
        checkAndGoDirection(directionToGo);
        return ReturnMessage.OK;
    }

    private ReturnMessage checkAndGoDirection(Direction direction) {
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
                    player.goDirection(direction);
                }else {
                    returnMessage = ReturnMessage.ROOM_DARK;
                    player.setRoomToUnlock(roomToVisit);
                }
            }
        }else{
            returnMessage = ReturnMessage.NO_ROOM_THIS_DIRECTION;
        }
        return returnMessage;
    }

    private void turnOnLight() {
        if (player.turnOnLight()){
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
    private ReturnMessage eatItem(String itemToEatName) {
        Item itemToEat = player.findInventoryItem(itemToEatName);
        ReturnMessage returnMessage = player.eatItem(itemToEat);
        if (returnMessage == ReturnMessage.OK){
            UI.eatItem((FoodItem) itemToEat);
        }
        return returnMessage;
    }


    private void gameOver() {
        if (UI.tryAgain()){
            startAdventure();
        }else{
            endGame();
        }
    }

    private void endGame() {
        UI.exitMessage();
        shouldRun = false;
    }
}