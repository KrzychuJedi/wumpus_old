package wumpus;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Student
 * Date: Aug 17, 2004
 * Time: 5:47:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class MockCommandable implements Commandable {

    private List calls = new ArrayList();

    public List getCalls() {
        return calls;
    }

    public boolean goEast() {
        calls.add("goEast");
        return true;
    }

    public boolean goWest() {
        calls.add("goWest");
        return true;
    }

    public boolean goNorth() {
        calls.add("goNorth");
        return true;
    }

    public boolean goSouth() {
        calls.add("goSouth");
        return true;
    }

    public void shootMagicArrow(String direction, String turn) {
        calls.add("shootMagic");
    }

    public void quit() {
        calls.add("quit");
    }
}
