package wumpus;
/* the test game map
( 1 )--( 2x)--( 3 )--( 4 )    x - Exit
         |      |      |      p - Pit
( 5p)--( 6 )--( 7 )--( 8w)    w - The smelly ferocious Wumpus
         |      |      |      * - your location
( 9 )--(10*)--(11 )--(12 )    b - Bats
  |             |     |
(13 )--(14b)--(15 )--(16p)
  |
(17 )
*/


public class WumpusGameCreator
{
    public static WumpusGame createTestGame(GameReporter gameReporter)
    {
        WumpusGame g = new WumpusGame(createTestMap(), gameReporter);
        g.setWumpusRoom(8);
        g.setPlayerRoom(10);
        g.setExit(2);
        g.setPit(16);
        g.setPit(5);
		g.setBat(14);

        return g;
    }
    public static WumpusGame createGame(GameReporter gameReporter, RoomMap roomMap)
    {
        return new WumpusGame(roomMap, gameReporter);
    }

	public static RoomMap createTestMap()
	{
		RoomMap roomMap = new RoomMap();
		roomMap.connect(1,2,Direction.EAST);
		roomMap.connect(2,3,Direction.EAST);
		roomMap.connect(2,6,Direction.SOUTH);
		roomMap.connect(3,4,Direction.EAST);
		roomMap.connect(3,7,Direction.SOUTH);
		roomMap.connect(4,8,Direction.SOUTH);
		roomMap.connect(5,6,Direction.EAST);
		roomMap.connect(6,7,Direction.EAST);
		roomMap.connect(6,10,Direction.SOUTH);
		roomMap.connect(7,8,Direction.EAST);
		roomMap.connect(7,11,Direction.SOUTH);
		roomMap.connect(8,12,Direction.SOUTH);
		roomMap.connect(9,10,Direction.EAST);
		roomMap.connect(9,13,Direction.SOUTH);
		roomMap.connect(10,11,Direction.EAST);
		roomMap.connect(11,12,Direction.EAST);
		roomMap.connect(11,15,Direction.SOUTH);
		roomMap.connect(12,16,Direction.SOUTH);
		roomMap.connect(13,14,Direction.EAST);
		roomMap.connect(14,15,Direction.EAST);
        roomMap.connect(15,16,Direction.EAST);
        roomMap.connect(13,17,Direction.SOUTH);
		return roomMap;
    }
}

