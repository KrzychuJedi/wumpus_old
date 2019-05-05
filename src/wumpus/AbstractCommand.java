package wumpus;

import java.util.List;
import java.util.ArrayList;

public abstract class AbstractCommand implements Command {

    private List args = new ArrayList();

    public void clear() {
        args.clear();
    }
    
    public List getArgs() {
        return args;
    }

    public void addArg(String arg) {
        args.add(arg);
    }

    public boolean validate(){
        return true;
    }

}
