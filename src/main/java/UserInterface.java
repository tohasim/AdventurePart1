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
                "-Inventory: show inventory\n" +
                "-Turn on light: light a dark room \n" +
                "-Unlock: unlock a locked door\n" +
                "-Help: get this message \n" +
                "-Exit: exit the game");
    }

    public String PromptUserChoice() {
        System.out.println("Please enter a command: ");
        return keyboard.nextLine();
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
                    availableDirections += currentRoom.getDirection(triedRoom) + " and ";
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
            String answer = PromptUserChoice();
            switch (answer.toLowerCase()){
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

    public void enterNewRoom(Room enteredRoom, String direction) {
        System.out.printf("You went %s, and entered a new room\n", direction);
        PrintDescription(enteredRoom);
    }
    public void enterRoom(Room enteredRoom, String direction){
        System.out.printf("You went %s, and entered %s \n", direction, enteredRoom.getName());
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

    public void printMessage(String stringToPrint) {
        System.out.println(stringToPrint);
    }
}
