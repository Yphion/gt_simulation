package core;

import java.util.*;

public class RandomIterationManager extends IterationManager{
	

	/**
	 * Creates a random matching and runs the games on it.
	 * @param players List of players
	 * @param GameManager GameManager to run the games
	 *
	 */
	public RandomIterationManager(List<Player> players, GameManager gm) {
		super(players, gm);
	}

	public int runIteration() {
		gm.success = 0;
		gm.scams = 0;
		//long temp = System.currentTimeMillis();
		Collections.shuffle(players);
		
		int tax = 0;
		
//		int n = players.size();
//		while(n>1) {
//			Player p1 = swap(players, n);
//			n--;
//			Player p2 = swap(players, n);
//			n--;
//			tax += gm.runGame(p1, p2);
//		}
		
		//time += (System.currentTimeMillis() - temp);
		
		for(int i=1; i<players.size(); i+=2) {
			tax += gm.runGame(players.get(i-1), players.get(i));
		}
		success = gm.success;
		scams = gm.scams;
		return tax;
	}
	
	public Player swap(List<Player> players, int n) {
		int rand = (int)(Math.random()*n);
		Player temp = players.get(rand);
		players.set(rand, players.get(n-1));
		players.set(n-1, temp);
		return temp;
	}
}
