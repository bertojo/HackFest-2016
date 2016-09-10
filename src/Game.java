import java.util.ArrayList;

public class Game {
	public long gameId;
	public ArrayList<Player> players;
	public static int voteTrack = 0;
	public int state;
	public ArrayList<Player> pendingMissionPlayers;
	public Map map;
	public int pmChannelCount;
	public int currentQuestNumber;
	
	//King stuff
	public Player king;
	//Counter for the next king
	public int kingCounter;
	
	//For approving of the mission players
	public int approveCount;
	
	//For mission success/failure
	public int successCount;
	public int failureCount;
	
	//For total number of wins
	//When total number of wins == 3, good guys win, need reveal assassin 
	public int totalWinCount;
	
	public int pmMissionCount;
	
	public Game(long id) {
		gameId = id;
		state = 1;
		players = new ArrayList<Player>();
		pendingMissionPlayers = new ArrayList<Player>();
	}
}
