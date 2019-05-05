package wumpus;

/**
 * Created by IntelliJ IDEA.
 * User: Student
 * Date: Aug 17, 2004
 * Time: 5:03:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class MoveNorthCommand extends AbstractCommand {

    public void execute(Commandable game) {
        game.goNorth();
    }
}
