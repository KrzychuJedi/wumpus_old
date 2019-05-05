package wumpus;

public class MoveEastCommand extends AbstractCommand {

    public void execute(Commandable game)
    {
        game.goEast();
    }
}
