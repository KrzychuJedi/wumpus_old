package wumpus;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Student
 * Date: Aug 18, 2004
 * Time: 8:32:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class ShootMagicCommand extends AbstractCommand {

     public void execute(Commandable game) {
        game.shootMagicArrow("","");
    }


    public boolean validate() {
        List args = getArgs();
        if(args.size()!=2)
            return false;

        boolean hasBothArgumentsValid = (validateDirectionArgument((String)args.get(0))
                    && validateDirectionArgument((String)args.get(1)));

        return hasBothArgumentsValid;
    }

    private boolean validateDirectionArgument(String arg) {
        return "NWES".indexOf(arg.toUpperCase())>-1;
    }
}
