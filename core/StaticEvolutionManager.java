package core;

import java.util.ArrayList;
import java.util.List;

import players.AlwaysCooperate;
import players.MafiaDefect;
import players.MixedOeco;
import players.TruthfulRandom;

public class StaticEvolutionManager extends EvolutionManager {

	int[] dist;

	boolean whitewashing = false;

	int m = 5001;
	int index = 0;
	public int[] ev_tax = new int[m];
	public int[] ev_scams = new int[m];

	double[][] prop_tax = new double[4][m];
	
	public double[][] ev_inc = new double[4][m];
	public double[][] ev_cap = new double[4][m];
	public int[][] ev_pop = new int[4][m];
	public double[][] ev_trust = new double[4][m];
	public double[][] ev_puretrust = new double[4][m];
	public double[][] ev_objtrust = new double[4][m];
	
	public long[] c_tax = new long[m];
	
	public Player[] _p = new Player[1000];

	public StaticEvolutionManager(List<Player> players, IterationManager im) {
		super(players, im);
	}

	public StaticEvolutionManager(ArrayList<Player> players, IterationManager im, int[] dist) {
		this(players, im);
		this.dist = dist;
		this.iter = 100;
		this.rep = 5000;
		
	}

	public StaticEvolutionManager(ArrayList<Player> players, IterationManager im, int[] dist, boolean b, int m, Player[] _p) {
		this(players, im, dist);
		whitewashing = b;
		this.m = m;
		this._p = _p;
	}

	private Player getNewPlayer() {
		double rand = Math.random() * dist[3];
		if (rand < dist[0]) {
			return new AlwaysCooperate();
		} else if (rand < dist[1]) {
			return new TruthfulRandom();
		} else if (rand < dist[2]) {
			return new MafiaDefect();
		} else {
			return new MixedOeco();
		}
	}

	public void run() {
		
		ev_tax[index] = 0;
		ev_scams[index] = 0;
		
		for(int i=0; i<4; i++) {
			ev_pop[i][index] = dist[i]-(i > 0 ? dist[i-1] : 0);

			
			ev_inc[i][index] = 0;
			ev_cap[i][index] = 200;
			
			ev_trust[i][index] = 1.0;
			ev_puretrust[i][index] = 1.0;
			ev_objtrust[i][index] = 1.0;
		}
		index++;
		
		for (int i = 1; i <= rep; i++) {
			runCycle(i);
			// Utils.addData(players);
			// Utils.addEvoData(players, new ArrayList<Player>(), this);
		}

	}

	public void runCycle(int gen) {
		tax = 0;
		int maf_rep = 1;
		for (int i = 0; i < players.size(); i++) {
			Player ply = players.get(i);
			ply.trades = 0;
			ply.success = 0;
			ply.profit = 0;
			ply.scammed = 0;
			ply.prev_value = ply.value;

			// Whitewashing
			if (whitewashing && ply instanceof MafiaDefect) {
				if (ply.info.getRep() < 0.5) {
					ply.info.reset();
				}
//				if (ply.value >= 400 && maf_rep > 0) {
//					//maf_rep--;
//					//ply.value -= 200;
//					//players.add(new MafiaDefect());
//				}
			}
		}
		
		
		int rand = (int) (Math.random() * (players.size()));
		players.get(rand).reset();
		
		success = 0;
		long time = 0;
		im.time = 0;
		int scams = 0;
		for (int i = 0; i < iter; i++) {
			tax += im.runIteration();
			success += im.success;
			scams += im.scams;
			// time += im.time;
		}
		// System.out.println(im.time);
		alltime_tax += tax;

		
		/// trash.add(players.get(rand));
		// players.set(rand, getNewPlayer());
		// players.remove(rand);

		// players.add(getNewPlayer());

		/**
		 * public int[] ev_tax = new int[m]; 
		 * public int[] ev_scams = new int[m];
		 * 
		 * public int[][] ev_inc = new int[4][m]; 
		 * public int[][] ev_cap = new int[4][m];
		 * public int[][] ev_pop = new int[4][m]; 
		 * public int[][] ev_trust = new int[4][m]; 
		 * public int[][] ev_puretrust = new int[4][m]; 
		 * public int[][] ev_objtrust = new int[4][m];
		 * 
		 * 
		 * public List<Integer>[] list_inc = new ArrayList[4]; 
		 * public List<Integer>[] list_cap = new ArrayList[4];
		 * 
		 */

		if (true) {
			ev_tax[index] = (int) tax;
			ev_scams[index] = scams;
			c_tax[index] = alltime_tax;
			int j=0;
			for(int i=0; i<4; i++) {
				int n = 0;
				double inc = 0;
				double cap = 0;

				double trust = 0;
				double puretrust = 0;
				double objtrust = 0;
				

				for(; j<dist[i]; j++) {
					n++;
					inc += (_p[j].value - _p[j].prev_value);
					
					cap += _p[j].value;
					
					if(_p[j].value >= im.gm.tax) {
						ev_pop[i][index]++;
					}
					
					trust += _p[j].info.rep;
					puretrust += _p[j].info.getPureRep();
					objtrust += _p[j].info.obj_rep;
					
					prop_tax[i][index] += _p[j].trades*im.gm.tax;
				}
				
				ev_inc[i][index] = 1.0*inc/n;
				ev_cap[i][index] = 1.0*cap/n;
				
				ev_trust[i][index] = trust/n;
				ev_puretrust[i][index] = puretrust/n;
				ev_objtrust[i][index] = objtrust/n;
			}
			index++;
		}

	}

}
