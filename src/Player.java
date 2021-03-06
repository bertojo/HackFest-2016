
public class Player {
	public String role;
	public boolean isKing;
	public String name;
	public long playerId;
	public long id;
	
	public Player(long id, String name) {
		this.playerId = id;
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Player) {
			Player player = (Player)obj;
			return id==player.id && name.equals(player.name);
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return name;
	}
}
