import Enums.Direction;
import Enums.ReturnMessage;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Scanner keyboard;
    public UserInterface(){
        keyboard = new Scanner(System.in);
    }

    public void Welcome() {
        System.out.println("Welcome to my adventure game.");
    }

    public void ExplainGame() {
        System.out.println("In this game you are an adventurer, who needs to find the goal room. \n" +
                "You do this by going from room to room, through a maze, until you reach the objective.");
    }

    public void Help() {
        System.out.println("The following commands are available: \n" +
                "-Look: get a description of the room you are in \n" +
                "-Go {direction}: go to a room in the specified direction.\n" +
                "  directions:\n" +
                "    north\n" +
                "    south\n" +
                "    east\n" +
                "    west\n" +
                "-Take {item}: take the item specified in {item}\n" +
                "-Place {item}: place the item specified in {item} from your inventory to the room\n" +
                "-Eat {food item}: eat a piece of food from your inventory\n" +
                "-Inventory: show inventory\n" +
                "-Turn on light: light a dark room \n" +
                "-Unlock: unlock a locked door\n" +
                "-Help: get this message \n" +
                "-Exit: exit the game");
    }

    public ArrayList<String> PromptUserChoice() {
        System.out.println("Please enter a command: ");
        String[] input = keyboard.nextLine().toLowerCase().trim().split(" ");
        ArrayList<String> returnList = new ArrayList<>();
        for (String word : input) {
            returnList.add(word);
        }
        return returnList;
    }

    public void PrintDescription(Room currentRoom) {
        System.out.printf("You are in the room called \"%s\":\n" +
                "%s\n", currentRoom.getName(), currentRoom.getDescription());
        if (!currentRoom.getItems().isEmpty()){
            String availableItems = "This room contains ";
            ArrayList<Item> items = currentRoom.getItems();
            for (Item item : items) {
                if (items.indexOf(item) != items.size() - 2){
                    availableItems += item + ", ";
                }else {
                    availableItems += item + " and ";
                }
            }
            availableItems = availableItems.substring(0, availableItems.length() - 2);
            System.out.println(availableItems);
        }
        if (!currentRoom.triedRooms.isEmpty()){
            String availableDirections = "You have found doors to the ";
            for (Room triedRoom : currentRoom.triedRooms) {
                if (triedRoom != null)
                    availableDirections += currentRoom.getDirection(triedRoom).toString().toLowerCase() + " and ";
            }
            availableDirections = availableDirections.substring(0, availableDirections.length() - 5);
            System.out.println(availableDirections);
        }
    }

    public void ExitMessage() {
        System.out.println("Thank you for playing");
    }

    public boolean TryAgain() {
        boolean answered = false;
        boolean tryAgain = false;
        while(!answered){
            System.out.println("Want to play again? (Yes/No)");
            ArrayList<String> answer = PromptUserChoice();
            switch (answer.get(0).toLowerCase()){
                case "yes" -> {
                    tryAgain = true;
                    answered = true;
                }
                case "no" -> answered = true;
                default -> System.out.println("Please write either \"Yes\" or \"No\"");
            }
        }
        return tryAgain;
    }

    public void enterNewRoom(Room enteredRoom, Direction direction) {
        System.out.printf("You went %s, and entered a new room\n", direction.toString().toLowerCase());
        PrintDescription(enteredRoom);
    }
    public void enterRoom(Room enteredRoom, Direction direction){
        System.out.printf("You went %s, and entered %s \n", direction.toString().toLowerCase(), enteredRoom.getName());
    }

    public void turnOnLight(Room currentRoom) {
        System.out.println("The light has been turned on, you can now see the room");
        PrintDescription(currentRoom);
    }

    public void takeItem(Item item) {
        System.out.printf("You took %s\n", item);
    }

    public void placeItem(Item item) {
        System.out.printf("You placed %s in the room.\n", item);
    }
    public void printInventory(ArrayList<Item> inventory){
        System.out.println("Your inventory: ");
        for (Item item : inventory) {
            System.out.printf(" -%s\n", item);
        }
    }

    public void printMessage(ReturnMessage returnMessage) {
        switch (returnMessage){
            case UNKNOWN_COMMAND -> System.out.println("Unknown command, type \"help\" for a list of commands");
            case NO_LOCKED_ROOMS -> System.out.println("No locked rooms nearby");
            case ROOM_LOCKED -> System.out.println("Room is locked");
            case ROOM_DARK -> System.out.println("The room you entered is dark, go back, or turn on the light");
            case NO_ROOM_THIS_DIRECTION -> System.out.println("You cannot go this way");
            case NO_ITEM_INVENTORY -> System.out.println("No item like that was found in your inventory");
            case NO_ITEM_ROOM -> System.out.println("No item like that was found in the room");
            case OK -> System.out.println("");
            case ITEM_NOT_FOOD -> System.out.println("This item cannot be eaten");
            case NOT_A_DIRECTION -> System.out.println("Input was not a direction, please choose either north, south, east or west");
            case ITEM_NOT_WEAPON -> System.out.println("This item is not a weapon");
            case ATTACK_UNSUCCESSFUL -> System.out.println("Attack unsuccessful, do you have a weapon equipped? Or maybe it's out of ammo?");
            case WEAPON_OUT_OF_AMMO -> System.out.println("Out of ammo!");
            case NO_WEAPON_EQUIPPED -> System.out.println("You do not have a weapon equipped");
        }
    }

    public void eatItem(String itemToTake) {
        System.out.printf("You ate %s\n", itemToTake);
    }

    public void printHealth(int hp) {
        System.out.printf("Your health is at %d\n", hp);
    }

    public void EquipItem(String itemToEquip) {
        System.out.printf("%s equipped!\n", itemToEquip);
    }

    public void attack() {
        System.out.println("You attacked the dangerous room");
    }
}
