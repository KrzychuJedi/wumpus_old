package wumpus;

/**
 * Created by IntelliJ IDEA.
 * User: Student
 * Date: Aug 16, 2004
 * Time: 3:20:19 PM
 * To change this template use File | Settings | File Templates.
 */
public    class Arrow
    {
        private String direction;
        private String turnDirection;
        private boolean turned;

    public Arrow(String direction) {
            this(direction.toUpperCase(), null);
        }

        public Arrow(String direction, String turn) {
            this.direction = direction;
            this.turnDirection = turn;
        }

        public String getTurnDirection() {
            return turnDirection;
        }

        public boolean isMagic() {
            return this.turnDirection != null;
        }

        public String getDirection() {
            if (this.turned)
            {
                return turnDirection;
            }
            else
            {
                return direction;
            }
        }

    public boolean hasTurned() {
        return this.turned;
    }

    public boolean turn() {

        if(oppositeDirection())
            return false;

        this.turned = true;
        return turned;
    }
    private boolean checkOpposites(String dir, String oppDir)
    {
        if(direction.equals(dir))
        {
            if(turnDirection.equals(oppDir))
                return true;
        }
        return false;


    }
    private boolean oppositeDirection()
    {
        if(checkOpposites(Direction.NORTH, Direction.SOUTH) ||
            checkOpposites(Direction.SOUTH, Direction.NORTH) ||
            checkOpposites(Direction.EAST, Direction.WEST) ||
            checkOpposites(Direction.WEST, Direction.EAST))
            return true;
       return false;

    }
}
