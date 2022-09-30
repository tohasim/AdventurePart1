public class Adventure {
    private boolean shouldRun;
    void MainLoop(UserInterface UI, Player player, Controller controller) {
        shouldRun = true;
        while(shouldRun){
            String userChoice = UI.PromptUserChoice();
            switch (userChoice.toLowerCase()){
                case "go north", "north", "n" -> player.GoDirection("north");
                case "go south", "south", "s" -> player.GoDirection("south");
                case "go east", "east", "e" -> player.GoDirection("east");
                case "go west", "west", "w" -> player.GoDirection("west");
                case "look" -> UI.PrintDescription(player.getCurrentRoom());
                case "help" -> UI.Help();
                case "exit" -> EndGame(UI);
                case "unlock" -> player.Unlock();
                case "turn on light" -> player.TurnOnLight();
                default -> System.out.println("Unknown command, type \"help\" for a list of commands");
            }
            if (player.getCurrentRoom().getName().equals("Ninth room") || player.getCurrentRoom().getName().equals("GOAAAAAL"))
                GameOver(UI, controller);
        }
    }

    private void GameOver(UserInterface UI, Controller controller) {
        if (UI.TryAgain()){
            controller.StartAdventure();
        }else{
            EndGame(UI);
        }
    }

    private void EndGame(UserInterface UI) {
        UI.ExitMessage();
        shouldRun = false;
    }

}
