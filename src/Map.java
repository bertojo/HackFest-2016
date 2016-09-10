
public class Map {
	public int totalNumberOfPlayers;
	public int[] missionPlayerCount;
	public int[] failureCount;
	public int currentQuestNumber;
	public static Map[] maps;

	private int[] fiveP = { 2, 3, 2, 3, 3 };
	private int[] fiveF = { 1, 1, 1, 1, 1 };
	private int[] sixP = { 2, 3, 4, 3, 4 };
	private int[] sixF = { 1, 1, 1, 1, 1 };
	private int[] sevenP = { 2, 3, 3, 4, 4 };
	private int[] sevenF = { 1, 1, 1, 2, 1 };
	private int[] eightP = { 3, 4, 4, 5, 5 };
	private int[] eightF = { 1, 1, 1, 2, 1 };
	private int[] nineP = { 3, 4, 4, 5, 5 };
	private int[] nineF = { 1, 1, 1, 2, 1 };
	private int[] tenP = { 3, 4, 4, 5, 5 };
	private int[] tenF = { 1, 1, 1, 2, 1 };

	public Map(int total, int[] mission, int[] fail) {
		switch (total) {
		case 5:
			totalNumberOfPlayers = 5;
			missionPlayerCount = fiveP;
			failureCount = fiveF;
			break;
		case 6:
			totalNumberOfPlayers = 6;
			missionPlayerCount = sixP;
			failureCount = sixF;
			break;
		case 7:
			totalNumberOfPlayers = 7;
			missionPlayerCount = sevenP;
			failureCount = sevenF;
			break;
		case 8:
			totalNumberOfPlayers = 8;
			missionPlayerCount = eightP;
			failureCount = eightF;
			break;
		case 9:
			totalNumberOfPlayers = 9;
			missionPlayerCount = nineP;
			failureCount = nineF;
			break;
		case 10:
			totalNumberOfPlayers = 10;
			missionPlayerCount = tenP;
			failureCount = tenF;
			break;
		default:
			break;
		}
	}
}
