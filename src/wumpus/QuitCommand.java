package wumpus;

public class QuitCommand extends AbstractCommand
{

     public void execute(Commandable game)
    {
        game.quit();
    }
}
