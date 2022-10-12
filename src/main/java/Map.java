import java.util.ArrayList;

public class Map {
    private Room roomOne;
    private Room roomTwo;
    private Room roomThree;
    private Room roomFour;
    private Room roomFive;
    private Room roomSix;
    private Room roomSeven;
    private Room roomEight;
    private Room roomNine;

    public Map() {
        createRooms();
        setConnections();
        lockRooms();
        turnOffLights();
        fillRoomsWithItems();
    }

    private void fillRoomsWithItems() {
        ArrayList<Item> roomOneItems = new ArrayList<>();
        roomOneItems.add(new Item("a", "feather"));
        roomOneItems.add(new MeleeWeapon("a", "sword", 10));
        roomOneItems.add(new RangedWeapon("a", "bow", 10, 10));
        roomOne.addItems(roomOneItems);

        ArrayList<Item> roomTwoItems = new ArrayList<>();
        roomTwoItems.add(new Item("a", "broken key"));
        roomTwo.addItems(roomTwoItems);
        Weapon enemyWeapon = new MeleeWeapon("a", "club", 10);
        Enemy orc = new Enemy("orc", 30, enemyWeapon);
        roomTwo.addEnemy(orc);

        ArrayList<Item> roomThreeItems = new ArrayList<>();
        roomThreeItems.add(new Item("a", "broken lamp"));
        roomThreeItems.add(new FoodItem("an", "apple", 5));
        roomThree.addItems(roomThreeItems);

        ArrayList<Item> roomFourItems = new ArrayList<>();
        roomFourItems.add(new Item("a", "lighter"));
        roomFourItems.add(new Item("glasses"));
        roomFourItems.add(new FoodItem("Bananas", 20));
        roomFour.addItems(roomFourItems);

        Item roomSixItems = new FoodItem("a", "protein bar", 10);
        roomSix.addItems(roomSixItems);
    }

    private void turnOffLights() {
        roomTwo.turnOffLight();
        roomEight.turnOffLight();
    }

    private void lockRooms() {
        roomFour.lockRoom();
        roomFive.lockRoom();
    }

    private void setConnections() {
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

    private void createRooms() {
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
