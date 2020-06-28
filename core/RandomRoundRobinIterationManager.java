package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomRoundRobinIterationManager extends IterationManager{
	/**
	 * Performs games on every possible matching in a random order.
	 * @param players List of players
	 * @param GameManager GameManager to run the games
	 *
	 */
	public RandomRoundRobinIterationManager(List<Player> players, GameManager gm) {
		super(players, gm);
	}

	public int runIteration() {
		gm.success = 0;
		int tax = 0;
		ArrayList<Player[]> p = new ArrayList<Player[]>();
		for(int i=0; i<players.size(); i++) {
			for(int j=i+1; j<players.size(); j++) {
				p.add(new Player[]{players.get(i),players.get(j)});
			}
		}
		Collections.shuffle(p);
		for(Player[] x : p) {
			tax += gm.runGame(x[0], x[1]);
		}
		success = gm.success;
		return tax;
	}
}
