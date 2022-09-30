import java.util.ArrayList;

public class Creator {
    private Room roomOne;
    private Room roomTwo;
    private Room roomThree;
    private Room roomFour;
    private Room roomFive;
    private Room roomSix;
    private Room roomSeven;
    private Room roomEight;
    private Room roomNine;

    public Creator() {
        CreateRooms();
        SetConnections();
        LockRooms();
        TurnOffLights();
        FillRoomsWithItems();
    }

    private void FillRoomsWithItems() {
        Item roomOneItem = new Item("a", "feather");
        roomOne.addItems(roomOneItem);

        ArrayList<Item> roomTwoItems = new ArrayList<>();
        roomTwoItems.add(new Item("a", "broken key"));
        roomTwo.addItems(roomTwoItems);

        ArrayList<Item> roomThreeItems = new ArrayList<>();
        roomThreeItems.add(new Item("a", "broken lamp"));
        roomThreeItems.add(new Item("a", "sword"));
        roomThree.addItems(roomThreeItems);

        ArrayList<Item> roomFourItems = new ArrayList<>();
        roomFourItems.add(new Item("a", "lighter"));
        roomFourItems.add(new Item("glasses"));
        roomFourItems.add(new Item("sneakers"));
        roomFour.addItems(roomFourItems);
    }

    private void TurnOffLights() {
        roomTwo.turnOffLight();
        roomEight.turnOffLight();
    }

    private void LockRooms() {
        roomFour.lockRoom();
        roomFive.lockRoom();
    }

    private void SetConnections() {
        roomOne.setSouth(roomFour);
        roomOne.setEast(roomTwo);
        roomThree.setWest(roomTwo);
        roomFour.setSouth(roomSeven);
        roomFive.setSouth(roomEight);
        roomSix.setNorth(roomThree);
        roomSix.setSouth(roomNine);
        roomSeven.setEast(roomEight);
        roomEight.setEast(roomNine);
    }

    private void CreateRooms() {
        roomOne = new Room("First room");
        roomOne.setDescription("This is the first room, try to find more rooms");

        roomTwo = new Room("Second room");
        roomTwo.setDescription("This is the second room, it's kind of dark in here");

        roomThree = new Room("Third room");
        roomThree.setDescription("This is the third room. It smells.");

        roomFour = new Room("Fourth room");
        roomFour.setDescription("This is the fourth room, there's a sleeping orc in the corner... Shhh");

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
    }

    public Room getRoomOne() {
        return roomOne;
    }
}
