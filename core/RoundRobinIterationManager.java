package core;

import java.util.*;


public class RoundRobinIterationManager extends IterationManager{
	
	/**
	 * Performs games on every possible matching in a semi-deterministic order.
	 * It will match in a proper round robin fashion such that players are always matched against players with an equivalent number of rounds played.
	 * @param players List of players
	 * @param GameManager GameManager to run the games
	 * @see https://en.wikipedia.org/wiki/Round-robin_tournament
	 */
	public RoundRobinIterationManager(List<Player> players, GameManager gm) {
		super(players, gm);
	}
	


	public int runIteration() {
		gm.success = 0;
		Collections.shuffle(players);
		int tax = 0;
		for(int i=1; i<players.size()/2; i++) {
			for(int j=0; j<=i; j++) {
				for(int k=j; k<players.size(); k = k+i+1) {
					tax += gm.runGame(players.get(k), players.get((k+i) % players.size()));
				}
			}
		}
		if(players.size() %2 == 1) {
			int i = players.size()/2;
			for(int j=0; j<=i; j++) {
				for(int k=j; k<players.size(); k = k+i+1) {
					tax += gm.runGame(players.get(k), players.get((k+i) % players.size()));
				}
			}
		} else {
			int i = players.size()/2;
			for(int j=0; j<=i; j++) {
				for(int k=j; k<players.size()/2; k = k+i+1) {
					tax += gm.runGame(players.get(k), players.get((k+i) % players.size()));
				}
			}
		}
		success = gm.success;
		return tax;
	}
}
