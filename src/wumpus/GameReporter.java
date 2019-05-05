package wumpus;

public interface GameReporter {
    public void exit(String dir);

    public void smellWumpus();

    public void bats();

    public void pit();

    public void win();

    public void winByKillingWumpus();

    public void fellInPit();

    public void lose();

    public void carriedByBats();

    public void move(boolean moveResult);

    void moveUnSuccessful();

    void moveSuccessful();

    void eatenByWumpus();

    public void miss();

    void outOfArrows();

    void pickedUpArrow(int arrowsPickedUp);

    void pickedUpTorch();

    void seeWumpus(String dir);

    public void shotYourselfWithMagicArrow();

    void torchHasBurnedOut();

    void torchRepelledBat();
}
