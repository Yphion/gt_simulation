package core;

import java.util.ArrayList;
import java.util.List;

import players.AdaptiveCooperate;
import players.AlwaysCooperate;
import players.AlwaysDefect;
import players.EvilDefect;
import players.HomoOeconomicus;
import players.MafiaDefect;
import players.MixedOeco;
import players.SafeCooperate;
import players.TruthfulRandom;

public class TaxationEvolutionManager extends EvolutionManager {

	//params
	double pct = 0.0;
	TaxationModel tm = TaxationModel.Additive;
	int[] dist;
	
	int norm;
	
	/**
	 * Performs selection assisted by taxation and repopulates by drawing uniformly.
	 * 
	 * 
	 * @param players List of players
	 * @param im IterationManager that runs each Iteration
	 */
	public TaxationEvolutionManager(List<Player> players, IterationManager im) {
		super(players, im);
		norm = players.get(0).value;
		
	}
	
	public TaxationEvolutionManager(ArrayList<Player> players, IterationManager im, int[] dist) {
		this(players, im);
		this.dist = dist;
		for(int i=1; i<4; i++) {
			dist[i] += dist[i-1];
		}
	}

	//Player[] pl = new Player[] {new AlwaysCooperate(), new TruthfulRandom(), new MafiaDefect(), new MixedOeco() };
	
	private Player getNewPlayer() {
		double rand = Math.random()*100;
		if(rand < dist[0]) {
			return new AlwaysCooperate();
		} else if(rand < dist[1]) {
			return new TruthfulRandom();
		} else if(rand < dist[2]) {
			return new MafiaDefect();
		} else {
			return new MixedOeco();
		}
	}
	
	public void run() {
		for(int i=0; i<rep; i++) {
			runCycle(i);
			Utils.addData(players);
			Utils.addEvoData(players, trash, this);
		}

	}
	
	
	public void runCycle(int gen) {
		tax = 0;
		int maf_rep = 1;
		for(int i=0; i<players.size(); i++) {
			Player ply = players.get(i);
			ply.trades = 0;
			ply.success = 0;
			ply.profit = 0;
			ply.scammed = 0;
			ply.prev_value = ply.value;
			
			//Whitewashing
			if(ply instanceof MafiaDefect) {
				if(ply.info.getRep() < 0.6) {
					//ply.info = new PlayerInfo();
				}
				if (ply.value >= 400 && maf_rep > 0) {
					maf_rep--;
					ply.value -= 200;
					players.add(new MafiaDefect());
				}
			}
		}
		
		for(int i=0; i<iter; i++) {
			tax += im.runIteration();
		}
		success = im.success;
		
		int avg = 0;
		for(Player ply : players) {
			avg += ply.value;
		}
		if(players.size() == 0) return;
		avg /= players.size();

		int diff = 1;//Math.max(0, avg/players.size());
//		double rel = Math.min(1.0, 1+pct*((1.0*norm)/avg-1));
//		for(Player ply : players) {
//			ply.value = (tm == TaxationModel.Additive ? (int)(ply.value-pct*diff) : (int)(ply.value*rel));
//		}
		

		
		int pop = players.size();
		for(int i=players.size()-1; i>=0; i--) {
			if(players.get(i).value < im.gm.tax) {
				trash.add(players.remove(i));
			}
		}
		
		int remain = players.size();
		//diff = pop - remain;
		//diff = 1;
		diff = 1;
		if(gen >= 1000) {
			//diff = 0;
		}


		while(diff > 0) {
			diff--;
			players.add(getNewPlayer());
			//players.add(players.get((int)(Math.random()*remain)).getClass().newInstance());
			players.get(players.size()-1).value = norm;
		}
		

	}

}
