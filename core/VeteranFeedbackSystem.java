package core;

public class VeteranFeedbackSystem extends FeedbackSystem {

	int threshold = 100;
	double min_rep = 0.9;
	double junior_weight = 0.0;
	
	public VeteranFeedbackSystem(double f) {
		min_rep = f;
	}

	@Override
	protected void processFeedback(Player[] players, Feedback[] f) {
		// TODO Auto-generated method stub
		if(players[0].info.weight <= threshold && players[1].info.weight <= threshold) {
			for(int i=0; i<2; i++) {
				if(f[i] == Feedback.Positive) {
					players[i].info.pos_weight++;
				}
				players[i].info.weight++;
			}
		} else {
			for(int i=0; i<2; i++) {
				double temp = 1.0;
				// 
				if(players[1-i].info.weight <= threshold  || (players[1-i].info.weight > threshold && players[1-i].info.rep < min_rep)) {
					temp = junior_weight;
				}
				if(f[i] == Feedback.Positive) {
					players[i].info.pos_weight += temp;
				}
				players[i].info.weight += temp;
			}
		}
		
		for(Player ply: players) {
			if(ply.info.weight == 0.0) {
				continue;
			}
			ply.info.rep = ply.info.pos_weight/ply.info.weight;
		}
	}

	@Override
	protected void postprocessFeedback(Player[] players, Feedback[] f) {
		// TODO Auto-generated method stub

	}

}
