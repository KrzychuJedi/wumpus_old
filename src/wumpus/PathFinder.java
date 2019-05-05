package wumpus;

public class PathFinder {
    private final RoomMap roomMap;

    public PathFinder(RoomMap roomMap) {
        this.roomMap = roomMap;
    }

        public String getUnubstructedDirections(int currentRoom, int wumpusRoom) {
            String dir = getUnubstructedDirectionsRecursive(currentRoom,wumpusRoom, Direction.EAST);
            if (dir!=null) return dir;
            dir = getUnubstructedDirectionsRecursive(currentRoom,wumpusRoom,Direction.NORTH);
            if (dir!=null) return dir;
            dir = getUnubstructedDirectionsRecursive(currentRoom,wumpusRoom,Direction.WEST);
            if (dir!=null) return dir;
            dir = getUnubstructedDirectionsRecursive(currentRoom,wumpusRoom,Direction.SOUTH);
            return dir;

        }

        private String getUnubstructedDirectionsRecursive(int currentRoom, int wumpusRoom,String dir) {
            if (currentRoom==wumpusRoom)
                return dir;
            int nextRoom = roomMap.getConnectingRoom(currentRoom,dir);
            if (nextRoom==-1) return null;
            return getUnubstructedDirectionsRecursive(nextRoom,wumpusRoom,dir);

        }

}
