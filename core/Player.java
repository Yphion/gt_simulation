package core;

public abstract class Player implements PlayerInterface, Comparable<Player>{
	
	protected PlayerInfo info = new PlayerInfo();
	protected String name;
	protected String identifier;
	public int value = 200;
	
	public int trades = 0;
	public int profit = 0;
	public int scammed = 0;
	public int success = 0;
	
	public int prev_value = value;
	
	public String toString() {
		return name;
	}
	
	@Override
	public int compareTo(Player o) {
		return o.value-value;
	}
	
	public Player reset() {
		
		info.reset();
		value = 200;
		trades = 0;
		profit = 0;
		scammed = 0;
		prev_value = value;
		
		return this;
	}
}