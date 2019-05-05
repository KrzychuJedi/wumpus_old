package wumpus;

class TextGameReporter implements GameReporter
{
	public void exit(String dir)
	{
		System.out.println("There is an exit to the " + dir);
	}

    public void outOfArrows() {
        System.out.println("You are out of arrows.");
    }

    public void smellWumpus()
	{
		System.out.println("You smell the Wumpus.");
	}

    public void torchHasBurnedOut() {
        // TODO
    }

    public void torchRepelledBat() {
        System.out.println("Torch has repelled the bats");
    }

    public void bats()
	{
		System.out.println("You hear squeaking.");
	}
	public void pit()
	{
		System.out.println("You feel the wind.");
	}
	public void win()
	{
		System.out.println("You have escaped!!  YOU WIN!!");
	}
    public void winByKillingWumpus()
    {
        System.out.println("You killed the Wumpus, you win!!");
    }
    public void eatenByWumpus() {
        System.out.println("You scream in agony as the Wumpus tears open your abdomen and eats you liver with some fava beans and a nice chianti.");
    }

    public void miss()
    {
        System.out.println("You missed");
    }

    public void lose()
	{
		System.out.println("YOU LOSE!");
	}
    public void shotYourselfWithMagicArrow()
    {
        System.out.println("You've been impaled by your own magic arrow!");
    }
	public void fellInPit()
	{
		System.out.println("You fell in a pit.");
	}
    public void carriedByBats()
    {
        System.out.println("You have been carried by the bats");
    }
    public void move(boolean moveSucceeded)
    {
        if (!moveSucceeded)
            System.out.println("You can't go that way.");
    }
    public void moveUnSuccessful()
    {
        System.out.println("You can't go that way.");
    }

    public void seeWumpus(String dir) {
        System.out.println("You see the Wumpus to the " + dir + "!");
    }

    public void pickedUpTorch() {
        System.out.println("-You have a torch-");
    }

    public void moveSuccessful()
    {
    }

    public void pickedUpArrow(int numArrows)
    {
        if (numArrows == 1)
            System.out.println("You picked up an arrow.");
        else if (numArrows > 1)
            System.out.println("You picked up " + numArrows + " arrows.");
    }
}
