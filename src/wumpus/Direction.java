package wumpus;

public class Direction
{
    public final static String EAST = "E";
    public final static String WEST = "W";
    public final static String SOUTH = "S";
    public final static String NORTH = "N";


    private static RandomNumberGenerator randomNumberGenerator=new RealRandomNumberGenerator(4);

    public static void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        Direction.randomNumberGenerator = randomNumberGenerator;
    }

    public static String makePleasantDirectionName(String dir) {
        if (dir.equals(EAST))
            return "East";
        else if (dir.equals(WEST))
            return "West";
        else if (dir.equals(NORTH))
            return "North";
        else
            return "South";
    }
    public static String getOpposite(final String dir) {
        if (dir.equals(NORTH)) return SOUTH;
        else if (dir.equals(SOUTH)) return NORTH;
        else if (dir.equals(WEST)) return EAST;
        else if (dir.equals(EAST)) return WEST;
        else throw new IllegalArgumentException("unexpected dirrection: "+dir);
    }

    public static String getRandomDirection() {
        switch(randomNumberGenerator.next()) {
            case 0: return Direction.NORTH;
            case 1: return Direction.SOUTH;
            case 2: return Direction.EAST;
            case 3: return Direction.WEST;
            default: throw new IllegalArgumentException("unexpected random number");
        }
    }

}



