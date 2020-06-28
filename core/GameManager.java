package core;

public class GameManager {
	
	int invest;
	int gain;
	double r; //multiplier for the game
	double f; //fault probability
	GameInfo gi;
	int tax;
	
	FeedbackSystem fs;
	/**
	 * The GameManager is able to run games between two players.
	 * 
	 * @param gi GameInfo which describes the game
	 * @param fs 
	 */
	public GameManager(GameInfo gi, FeedbackSystem fs) {
		this.gi = gi;
		this.invest = gi.invest;
		this.gain = gi.gain;
		this.tax = gi.tax;
		this.r = gi.r;
		this.f = gi.f;
		this.fs = fs;
	}
	
	public int success = 0;
	public int scams = 0;
	
	public int runGame(Player p1, Player p2) {
		
		if(p1.value < tax || p2.value < tax) {
			
			return 0;
		}
		
		Player[] players = new Player[]{p1, p2};
		Feedback[] fb = new Feedback[2];
		StrategyMatrix[] stratMat = new StrategyMatrix[] {p1.getStrategy(gi, p2.info),p2.getStrategy(gi, p1.info)};
		
		if(stratMat[0].strat == Strategy.NoTrade || stratMat[1].strat == Strategy.NoTrade) {
			return 0;
		}
		
		for(Player ply : players) {
			ply.value -= tax;
			ply.trades++;
		}
		
		int coop = 0;
		int loser = 0;
		for(int i=0; i<2; i++) {
			int j = 1-i;
			
			int decision = stratMat[i].strat==Strategy.Cooperate?0:1;
			if(Math.random() < f) {
				decision = 1-decision;
			}
			
			if(decision == 0) { //Cooperate
				players[i].value -= invest;
				players[j].value += gain;
				players[i].info.addPositive();
				players[j].profit++;
				fb[i] = stratMat[j].f[0];
				coop++;
			} else { //Defect
				players[i].info.addNegative();
				loser = j;
				fb[i] = stratMat[j].f[1];
			}
		}
		
		if(coop == 2) {
			success++;
			for(Player ply : players) {
				ply.success++;
			}
		} else if(coop == 1) {
			players[loser].scammed++;
			scams++;
		}
		
		fs.setFeedback(players, fb);
		
		return 2*tax;
	}
	
}
