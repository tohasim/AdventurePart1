import java.util.Scanner;

public class UserInterface {
    Scanner keyboard;
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
                case "no" -> {
                    tryAgain = false;
                    answered = true;
                }
                default -> System.out.println("Please write either \"Yes\" or \"No\"");
            }
        }
        return tryAgain;
    }
}
