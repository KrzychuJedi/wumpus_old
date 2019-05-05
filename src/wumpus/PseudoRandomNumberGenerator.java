package wumpus;

/**
 * Created by IntelliJ IDEA.
 * User: Student
 * Date: Aug 15, 2004
 * Time: 11:04:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class PseudoRandomNumberGenerator implements RandomNumberGenerator {
    private int[] sequence;
    int index = 0;

    public PseudoRandomNumberGenerator(int[] sequence) {
        this.sequence = sequence;
    }

    public int next() {
        return sequence[index++];
    }
}
