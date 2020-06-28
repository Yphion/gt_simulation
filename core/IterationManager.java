package core;

import java.util.*;

public abstract class IterationManager {
	protected List<Player> players;
	public GameManager gm;
	public int success = 0;
	public int scams = 0;
	public long time = 0;
	
	public IterationManager(List<Player> players, GameManager gm) {
		this.players = players;
		this.gm = gm;
	}
	
	public int runIteration() {
		return 0;
	}
	
}
