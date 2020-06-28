package core;

import java.util.*;

import players.*;
public class EvolutionManager {
	
	protected List<Player> players;
	List<Player> trash = new ArrayList<Player>();
	IterationManager im;
	
	//params
	
	int iter = 1; //how many iterations before
	int kill = 1; //how many kills per iteration
	int rep = 2000; //repetition count of evolutionary cycle
	
	/**
	 * Performs natural selection and repopulates by drawing uniformly.
	 * 
	 * @param players List of players
	 * @param im IterationManager that runs each Iteration
	 */
	public EvolutionManager(List<Player> players, IterationManager im) {
		this.players = players;
		this.im = im;
		//PlayerInfo.writeThrough = true; //Update trust values immediately
	}
	
	public void run() {
		for(int i=0; i<rep; i++) {
			runCycle();
//			for(Player ply : players) {
//				ply.info.update();
//			}
			Utils.addData(players);
			Utils.addEvoData(players, trash, this);
		}

	}
	
	Player[] pl = new Player[] {new AdaptiveCooperate(), new AlwaysCooperate(), new AlwaysDefect(), new EvilDefect(), new HomoOeconomicus(), new MafiaDefect(), new SafeCooperate(), new SafeCooperate(), new TruthfulRandom()};
	public long alltime_tax = 0;
	public long tax = 0;
	public int success = 0;
	
	public void runCycle() {
		for(int i=0; i<iter; i++) {
			im.runIteration();
		}
		
		Collections.sort(players);
		int pop = players.size();
		int remain = Math.max(players.size() - kill,0);
		for(int i=players.size()-1; i>=remain; i--) {
			players.remove(i);
		}
		int avg = 0;
		for(int i=players.size()-1; i>=0; i--) {
			if(players.get(i).value < im.gm.gain+im.gm.tax) {
				players.remove(i);
			} else {
				avg += players.get(i).value;
			}
		}
		
		remain = players.size();
		avg /= remain;
		int diff = pop - remain;
		
		
		while(diff > 0) {
			diff--;
			try {
				players.add(pl[(int)(Math.random()*pl.length)].getClass().newInstance());
				//players.add(players.get((int)(Math.random()*remain)).getClass().newInstance());
				players.get(players.size()-1).value = avg;
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}
