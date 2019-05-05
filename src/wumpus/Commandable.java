package wumpus;

/**
 * Created by IntelliJ IDEA.
 * User: Student
 * Date: Aug 17, 2004
 * Time: 5:44:42 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Commandable {
    public boolean goEast();
    public boolean goWest();
    public boolean goNorth();
    public boolean goSouth();
    public void shootMagicArrow(String direction, String turn);
    public void quit();
}
