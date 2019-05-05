package wumpus;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Student
 * Date: Aug 17, 2004
 * Time: 3:48:30 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Command {

    List getArgs();

    void addArg(String arg);
    public void clear();
    public void execute(Commandable game);
    public boolean validate();
}
