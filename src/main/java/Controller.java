public class Controller {
    private UserInterface UI;
    private Adventure adventure;
    private Creator creator;
    private Player player;
    public void StartAdventure(){
        adventure = new Adventure();
        UI = new UserInterface();
        creator = new Creator();
        player = new Player(creator.getRoomOne(), UI);

        UI.Welcome();
        UI.ExplainGame();
        UI.Help();
        adventure.MainLoop(UI, player, this);
    }
}
