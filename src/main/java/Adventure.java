public class Adventure {
    boolean shouldRun = true;
    UserInterface UI;
    Room CurrentRoom;
    Room roomOne;
    Room roomTwo;
    Room roomThree;
    Room roomFour;
    Room roomFive;
    Room roomSix;
    Room roomSeven;
    Room roomEight;
    Room roomNine;
    void StartAdventure(){
        UI = new UserInterface();
        CreateRooms();
        CurrentRoom = roomOne;
        UI.Welcome();
        UI.ExplainGame();
        UI.Help();
        MainLoop();
    }

    private void CreateRooms() {
        roomOne = new Room("First room");
        roomOne.setDescription("This is the first room, try to find more rooms");

        roomTwo = new Room("Second room");
        roomTwo.setDescription("This is the second room, it's kind of dark in here");

        roomThree = new Room("Third room");
        roomThree.setDescription("This is the third room. It smells.");

        roomFour = new Room("Fourth room");
        roomFour.setDescription("This is the fourt room, there's a sleeping orc in the corner... Shhh");

        roomFive = new Room("GOAAAAAL");
        roomFive.setDescription("Congratulations! You've found the goal room");

        roomSix = new Room("Sixth room");
        roomSix.setDescription("This is the sixth room. It is just boring.");

        roomSeven = new Room("Seventh room");
        roomSeven.setDescription("This is the seventh room, there's a beautiful princess, however, she is not interested. :,(");

        roomEight = new Room("Eighth room");
        roomEight.setDescription("This is the eighth room. You are almost there!");

        roomNine = new Room("Ninth room");
        roomNine.setDescription("This is the ninth room, theres a devil mouth in the floor which swallowed you whole.");

        roomOne.setRooms(null, roomFour, roomTwo, null);
        roomTwo.setRooms(null, null, roomThree, roomOne);
        roomThree.setRooms(null, roomSix, null, roomTwo);
        roomFour.setRooms(roomOne, roomSeven, null, null);
        roomFive.setRooms(null, roomEight, null, null);
        roomSix.setRooms(roomThree, roomNine, null, null);
        roomSeven.setRooms(roomFour, null, roomEight, null);
        roomEight.setRooms(roomFive, null, roomNine, roomSeven);
        roomNine.setRooms(roomSix, null, null, roomEight);
    }

    private void MainLoop() {
        while(shouldRun){
            String userChoice = UI.PromptUserChoice();
            switch (userChoice.toLowerCase()){
                case ("go north") -> GoDirection("north");
                case ("go south") -> GoDirection("south");
                case ("go east") -> GoDirection("east");
                case ("go west") -> GoDirection("west");
                case ("look") -> UI.PrintDescription(CurrentRoom);
                case ("help") -> UI.Help();
                case ("exit") -> EndGame();
                default -> System.out.println("Unknown command, type \"help\" for a list of commands");
            }
            if (CurrentRoom.getName().equals("Ninth room") || CurrentRoom.getName().equals("GOAAAAAL"))
                GameOver();
        }
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

    private void GoDirection(String direction) {
        if (CurrentRoom.getRoom(direction) != null){
            CurrentRoom = CurrentRoom.getRoom(direction);
            System.out.printf("You went %s, and entered a new room\n", direction);
            UI.PrintDescription(CurrentRoom);
        }else{
            System.out.println("You cannot go this way");
        }
    }
}
