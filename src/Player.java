
public class Player {
	public String role;
	public boolean isKing;
	public String name;
	public int id;
	
	public Player(int id, String name) {
		this.id = id;
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
}
