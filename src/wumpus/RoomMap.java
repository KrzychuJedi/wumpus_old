package wumpus;
import java.util.*;

public class RoomMap
{
	private Map connections = new HashMap();


	public void connect(int from, int to, String dir)
	{
		connectOneWay(from, to, dir);
		connectOneWay(to, from, oppositeDirection(dir));
	}
	public int getNumberOfRooms()
    {
        return connections.size();
    }
	public int getConnectingRoom(int roomNumber, String direction)
	{
		String key = createKey(roomNumber, direction);
		Integer room = (Integer)connections.get(key);
		if (room != null)
			return room.intValue();
		else
			return -1;	
	}

	private String createKey(int room, String dir)
	{
		return room + dir.toUpperCase();
	}
	
	private void connectOneWay(int from, int to, String dir)
	{
		String key = createKey(from,dir);
		Integer toHolder = new Integer(to);
		connections.put(key, toHolder);
	}

	private String oppositeDirection(String dir)
	{
		if (dir.equals(Direction.EAST))
			return Direction.WEST;
		if (dir.equals(Direction.WEST))
			return Direction.EAST;
		if (dir.equals(Direction.NORTH))
			return Direction.SOUTH;
		if (dir.equals(Direction.SOUTH))
			return Direction.NORTH;
		return null;
	}
}
