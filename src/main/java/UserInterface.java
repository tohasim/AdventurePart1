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
                "-Go {direction}: go to a room in the specified direction.\n" +
                "  directions:\n" +
                "    north\n" +
                "    south\n" +
                "    east\n" +
                "    west\n" +
                "-Look: get a description of the room you are in \n" +
                "-Help: get this message \n" +
                "-Exit: exit the game");
    }

    public String PromptUserChoice() {
        System.out.println("Please enter a command: ");
        return keyboard.nextLine();
    }

    public void PrintDescription(Room currentRoom) {
        System.out.printf("You are in the room called \"%s\":\n" +
                "  %s\n", currentRoom.getName(), currentRoom.getDescription());
        if (!currentRoom.triedRooms.isEmpty()){
            String availableDirections = "There are doors to the ";
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
        currentRoom.turnOnLight();
    }
}
