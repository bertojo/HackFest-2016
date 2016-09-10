import java.util.ArrayList;

public class Game {
	public long gameId;
	public ArrayList<Player> players;
	public int voteTrack;
	public int state;
	public ArrayList<Player> pendingMissionPlayers;
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
	
	public Game(long id) {
		gameId = id;
		state = 1;
		players = new ArrayList<Player>();
		pendingMissionPlayers = new ArrayList<Player>();
	}
}
