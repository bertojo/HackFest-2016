import java.util.LinkedList;

public class Game {
	public int gameId;
	public LinkedList<Player> players;
	public int voteTrack;
	public int state;
	public LinkedList<Player> pendingMissionPlayers;
	public Map map;
	
	//King stuff
	public Player king;
	//Counter for the next king
	public int kingCounter;
	
	//For approving of the mission players
	public int approveCount;
	
	//For mission success/failure
	public int successCount;
	public int failureCount;
}
