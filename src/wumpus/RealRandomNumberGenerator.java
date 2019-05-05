package wumpus;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Student
 * Date: Aug 15, 2004
 * Time: 11:45:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class RealRandomNumberGenerator implements RandomNumberGenerator {
    private Random random = new Random();
    private int max ;

    public RealRandomNumberGenerator(int max)
    {
        this.max = max;
    }
    public int next() {
//         int numberOfRooms  = map.getNumberOfRooms();
//        if(numberOfRooms == 0)
//            numberOfRooms=1;
        return random.nextInt(max);
    }

}
