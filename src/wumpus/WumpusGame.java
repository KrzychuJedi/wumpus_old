package wumpus;

import java.util.*;

public class WumpusGame implements Commandable {
    public static final int NULL_ROOM = 0;
    private RoomMap roomMap = null;
    private HashSet pits = new HashSet();
    private HashSet bats = new HashSet();
    private int[] arrowLocations = initArrows();
    private int arrowsInQuiver = 5;
    private int magicArrowsInQuiver = 2;
    private GameReporter reporter;

    private RandomNumberGenerator randomNumberGenerator;

    private int currentRoom = NULL_ROOM;
    private int wumpusRoom = NULL_ROOM;
    private boolean wumpusDead = false;
    private int exit = NULL_ROOM;
    private static final int QUIVER = 0;
    private int torchRoom = NULL_ROOM;
    private boolean playerHasTorch = false;
    private int numberOfMovesWithTorch = 0;
    private boolean gameOver = false;

    public WumpusGame(RoomMap map, GameReporter reporter) {
        roomMap = map;
        int numberOfRooms=map.getNumberOfRooms();
        if (numberOfRooms==0) numberOfRooms=1;
        randomNumberGenerator = new RealRandomNumberGenerator(numberOfRooms);
        this.reporter = reporter;
    }

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public boolean goWest()  {return move(Direction.WEST);}
    public boolean goEast()  {return move(Direction.EAST);}
    public boolean goSouth() {return move(Direction.SOUTH);}
    public boolean goNorth() {return move(Direction.NORTH);}

    public boolean shootMagicArrow() {
        return false;
    }


    public void quit() {
        gameOver = true;
    }

    public boolean gameOver() {
        return gameOver;
    }

    private int[] initArrows() {
        int[] tempArrows = new int[5];
        for (int i = 0; i != tempArrows.length; i++)
            tempArrows[i] = QUIVER;

        return tempArrows;
    }

    private boolean move(String direction) {
        boolean result = true;
        int destinationRoom = getConnectingRoom(direction);
        if (destinationRoom > 0) {
            currentRoom = destinationRoom;
            result = true;
        } else
            result = false;

        reporter.move(result);


        if (isArrowInRoom(currentRoom))
            pickupArrow(currentRoom);

        if (torchRoom == currentRoom) {
            torchRoom = NULL_ROOM;
            playerHasTorch = true;
            reporter.pickedUpTorch();

        }

        if (playerHasTorch()) {
            numberOfMovesWithTorch++;
        }

        return result;
    }

    private void pickupArrow(int currentRoom) {
        int arrowsPickedUp = 0;
        for (int i = 0; i != arrowLocations.length; i++) {
            if (currentRoom == arrowLocations[i]) {
                arrowLocations[i] = QUIVER;
                arrowsPickedUp++;
                // TODO:  This is a bogus construct.  This is determinent.
                arrowsInQuiver++;
            }
        }
        reporter.pickedUpArrow(arrowsPickedUp);
    }

    public boolean isPlayerNearWumpus() {
        int east = getConnectingRoom(Direction.EAST);
        int west = getConnectingRoom(Direction.WEST);
        int north = getConnectingRoom(Direction.NORTH);
        int south = getConnectingRoom(Direction.SOUTH);

        return wumpusRoom == east
                || wumpusRoom == west
                || wumpusRoom == south
                || wumpusRoom == north;
    }

    public void setExit(int room) {
        exit = room;
    }

    public void setWumpusRoom(int room) {
        wumpusRoom = room;
    }

    public void setPlayerRoom(int to) {
        currentRoom = to;
    }

    public int getPlayerRoom() {
        return currentRoom;
    }

    public void setPit(int room) {
        pits.add(new Integer(room));
    }

    public void setPits(int[] rooms) {
        for (int i = 0; i < rooms.length; i++)
            setPit(rooms[i]);
    }

    public boolean pitIsInRoom(int room) {
        Integer pitInteger = new Integer(room);
        return pits.contains(pitInteger);
    }

    public void setBat(int room) {
        bats.add(new Integer(room));
    }

    public void moveBats(int fromRoom, int toRoom) {
        bats.remove(new Integer(fromRoom));
        setBat(toRoom);
    }

    public void setBats(int[] rooms) {
        for (int i = 0; i < rooms.length; i++)
            setBat(rooms[i]);
    }

    public boolean batsInRoom(int room) {
        Integer batInteger = new Integer(room);
        return bats.contains(batInteger);
    }

    public void evaluate() {
        if (numberOfMovesWithTorch > 3) {
            playerHasTorch = false;
            reporter.torchHasBurnedOut();
        }

        if (!evaluateCurrentRoom(reporter))
            evaluateAdjacentRooms(reporter);
        if (playerHasTorch() && numberOfMovesWithTorch > 1) {
            PathFinder pathFinder = new PathFinder(this.roomMap);
            String dir = pathFinder.getUnubstructedDirections(currentRoom, wumpusRoom);
            if (dir != null) {
                wumpusRoom = roomMap.getConnectingRoom(wumpusRoom, Direction.getOpposite(dir));
            }
        }

    }


    private boolean evaluateCurrentRoom(GameReporter reporter) {

        if (currentRoom == wumpusRoom) {
            reporter.eatenByWumpus();
            reporter.lose();
        } else if (wumpusDead) {
            reporter.winByKillingWumpus();
        } else if (pitIsInRoom(currentRoom)) {
            reporter.fellInPit();
            reporter.lose();
        } else if (batsInRoom(currentRoom)) {
            if (playerHasTorch()) {
                reporter.torchRepelledBat();
                int savedRoom=currentRoom;
                int batsNewRoom=getRandomNeighbourRoom();
                moveBats(savedRoom, batsNewRoom);
                evaluate();

            } else {
                reporter.carriedByBats();
                int savedRoom = currentRoom;

                currentRoom = randomNumberGenerator.next();

                int batsNewRoom;
                while ((batsNewRoom = randomNumberGenerator.next()) == wumpusRoom) {
// do nothing
                }
                moveBats(savedRoom, batsNewRoom);
                evaluate();
            }
        } else if (currentRoom == exit)
            reporter.win();
        else
            return false;

        return true;
    }

    private int getRandomNeighbourRoom() {
        //a room needs to habe a neighbour, so the loop will end after a while
        while(true) {
            String dir=Direction.getRandomDirection();
            int newRoom=roomMap.getConnectingRoom(currentRoom,dir);
            if (newRoom!=-1)
                return newRoom;
        }
    }

    private void evaluateAdjacentRooms(GameReporter r) {
        int room;
        String directions[] = new String[]{Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};

        for (int i = 0; i < directions.length; i++) {
            String dir = directions[i];
            if ((room = getConnectingRoom(dir)) >= 0) {
                r.exit(dir);
                evaluateHazards(r, room, Direction.makePleasantDirectionName(dir));
            }
        }
    }

    public void evaluateHazards(GameReporter r, int room, String dir) {
        if (pitIsInRoom(room))
            r.pit();
        if (batsInRoom(room))
            r.bats();
        if (wumpusRoom == room) {
            if (playerHasTorch)
                r.seeWumpus(dir);
            else
                r.smellWumpus();
        }
    }

    public void connect(int from, int to, String dir) {
        roomMap.connect(from, to, dir);
    }

    private int getConnectingRoom(String dir) {
        return roomMap.getConnectingRoom(currentRoom, dir);
    }

    public boolean isArrowInRoom(int room) {
        for (int i = 0; i != arrowLocations.length; i++)
            if (room == arrowLocations[i])
                return true;

        return false;
    }

    public void shootArrow(String dir) {
        if (haveEnoughArrows()) {
            decrementArrowsInQuiver();
            Arrow arrow = new Arrow(dir);
            hitWallOrKeepFlying(currentRoom, arrow);
        } else {
            reporter.outOfArrows();
        }
    }

    public void shootMagicArrow(String direction, String turn) {
        if (haveEnoughMagicArrows()) {
            decrementMagicArrowsInQuiver();
            Arrow arrow = new Arrow(direction, turn);
            hitWallOrKeepFlying(currentRoom, arrow);
        } else {
            reporter.outOfArrows();
        }
    }

    private void decrementMagicArrowsInQuiver() {
        magicArrowsInQuiver--;
    }

    private boolean haveEnoughMagicArrows() {
        return (magicArrowsInQuiver > 0);
    }

    private boolean haveEnoughArrows() {
        return (arrowsInQuiver > 0);
    }

    private void keepFlying(int currentArrowRoom, Arrow arrow) {
        if (wumpusRoom == currentArrowRoom)
            hitWumpus(currentArrowRoom);
        else
            hitWallOrKeepFlying(currentArrowRoom, arrow);
    }

    private void hitWallOrKeepFlying(int currentArrowRoom, Arrow arrow) {
        int room = roomMap.getConnectingRoom(currentArrowRoom, arrow.getDirection());

        if (noNextRoom(room)) {
            if (arrow.isMagic() && !arrow.hasTurned()) {
                if (arrow.turn())
                    hitWallOrKeepFlying(currentArrowRoom, arrow);
                else {
                    reporter.shotYourselfWithMagicArrow();
                    reporter.lose();
                }
            } else {
                hitWall(currentArrowRoom);
            }
        } else
            keepFlying(room, arrow);
    }

    private boolean noNextRoom(int room) {
        return room == -1;
    }

    private void hitWall(int room) {
        arrowLocations[getArrowSpot()] = room;
        if (!wumpusDead) {
            reporter.miss();
            moveWumpus();
        }
    }

    private void moveWumpus() {

        wumpusRoom = randomNumberGenerator.next();
        if (currentRoom == wumpusRoom) {
            reporter.eatenByWumpus();
        }
    }

    private void hitWumpus(int nextRoom) {
        wumpusDead = true;
        arrowLocations[getArrowSpot()] = nextRoom;
        evaluateCurrentRoom(reporter);
    }

    private int getArrowSpot() {
        for (int i = 0; i != arrowLocations.length; i++) {
            if (arrowLocations[i] == 0)
                return i;
        }
        return 0;
    }

    public void decrementArrowsInQuiver() {
        arrowsInQuiver--;
    }

    public int getArrowsLeft() {
        return arrowsInQuiver;
    }

    public boolean isWumpusDead() {
        return wumpusDead;
    }

    public int getWumpusRoom() {
        return wumpusRoom;
    }

    public boolean playerHasTorch() {
        return playerHasTorch;
    }

    public void putTorchInRoom(int room) {
        torchRoom = room;
    }

    public boolean torchInRoom(int room) {
        return room == torchRoom;
    }

    public int getMagicArrowsLeft() {
        return magicArrowsInQuiver;
    }

    public int getTorchRoom() {
        return torchRoom;
    }

    public boolean dropMagicArrow() {
        if (magicArrowsInQuiver > 0) {
            decrementMagicArrowsInQuiver();
            return true;
        }
        return false;
    }
}


