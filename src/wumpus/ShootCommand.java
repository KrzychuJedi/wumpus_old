package wumpus;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Student
 * Date: Aug 18, 2004
 * Time: 11:18:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class ShootCommand extends AbstractCommand {

    public void execute(Commandable game) {

    }

    public boolean validate() {
       List args = getArgs();
        if(args.size()!=1)
            return false;
        return validateDirectionArgument((String)args.get(0));
    }

    private boolean validateDirectionArgument(String arg) {
        return "NWES".indexOf(arg.toUpperCase())>-1;
    }
}
