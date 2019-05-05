package wumpus;

/**
 * Created by IntelliJ IDEA.
 * User: James
 * Date: May 16, 2004
 * Time: 2:45:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestGameReporter implements GameReporter {
    public boolean northExitFlag = false;
    public boolean southExitFlag = false;
    public boolean eastExitFlag = false;
    public boolean westExitFlag = false;
    public boolean wumpusFlag = false;
    public boolean pitFlag = false;
    public boolean batsFlag = false;
    public boolean winFlag = false;

    public void torchHasBurnedOut() {
        message += "-Your torch has gone out; you've dropped it-";
    }

    public boolean loseFlag = false;
    public boolean fellInPitFlag = false;
    public boolean moveTrueFlag = false;
    public boolean moveFalseFlag = false;
    private String message;
    public boolean outOfArrowsFlag = false;

    public TestGameReporter() {
        clear();
    }

    public void clear() {
        northExitFlag = false;
        southExitFlag = false;
        eastExitFlag = false;
        westExitFlag = false;
        wumpusFlag = false;
        pitFlag = false;
        batsFlag = false;
        winFlag = false;
        loseFlag = false;
        fellInPitFlag = false;
        moveTrueFlag = false;
        moveFalseFlag = false;
        message = "";
    }

    public void exit(String dir)
    {
        if (dir.equals(Direction.NORTH)) northExitFlag = true;
        if (dir.equals(Direction.SOUTH)) southExitFlag = true;
        if (dir.equals(Direction.EAST)) eastExitFlag = true;
        if (dir.equals(Direction.WEST)) westExitFlag = true;
    }

    public String getExits() {
        String result = "Passages:";
         if (northExitFlag)
            result += " North";
        if (southExitFlag)
            result += " South";
        if (eastExitFlag)
            result += " East";
        if (westExitFlag)
            result += " West";

        return result;
    }

    public void smellWumpus()
    {
        wumpusFlag = true;
        message += "-What's that smell-";
    }
    public void pit()
    {
        pitFlag = true;
        message += "-You feel the wind-";
    }
    public void bats()
    {
        batsFlag = true;
        message += "-You hear bats-";
    }
    public void win()
    {
        winFlag = true;
        message += "-You win-";
    }

    public void winByKillingWumpus()
    {
        winFlag = true;
        message += "-You killed the Wumpus, you win!!-";
    }

    public void eatenByWumpus() {
        message += "-You have been eaten by the Wumpus-";
    }

    public void miss()
    {
        message += "-You missed-";
    }

    public void lose()
    {
        loseFlag = true;
        message += "-You lose-";
    }
    public void shotYourselfWithMagicArrow()
    {
        message += "-You've been impaled by your own magic arrow!-";
    }
    public void fellInPit()
    {
        fellInPitFlag = true;
        message += "-You're falling-";
    }
    public void carriedByBats()
    {
        message += "-You have been carried by the bats-";
    }
    public void move(boolean moveSucceeded)
    {
        if (moveSucceeded)
            moveSuccessful();
        else
            moveUnSuccessful();
    }

    public void moveUnSuccessful() {
        moveFalseFlag = true;
        message += "-You can't go that way-";

    }

    public void moveSuccessful() {
        moveTrueFlag = true;
    }

    public String getMessages() {
        String retVal = message;
        clear();
        return retVal;
    }

    public void seeWumpus(String dir) {
        message += "-You see the Wumpus to the " + dir + "!-";
    }

    public void pickedUpTorch() {
        message += "-You have a torch-";
    }

    public void pickedUpArrow(int numArrows)
    {
        if (numArrows == 1)
            message += "-You picked up an arrow-";
        else if (numArrows > 1)
            message += "-You picked up " + numArrows + " arrows-";
    }

    public void outOfArrows() {
        outOfArrowsFlag = true;
        message += "-You are out of arrows-";
    }
    public void torchRepelledBat() {
        message += "-Torch has repelled the bats-";
    }

}
