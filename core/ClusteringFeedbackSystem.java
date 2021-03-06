package core;

public class ClusteringFeedbackSystem extends FeedbackSystem{

	int threshold = 100;
	double min_rep = 0.8;
	double max_rep = 0.6;
	
	
	@Override
	protected void processFeedback(Player[] players, Feedback[] f) {
		// TODO Auto-generated method stub
		if(players[0].info.pos_weight <= threshold && players[1].info.pos_weight <= threshold) {
			for(int i=0; i<2; i++) {
				if(f[i] == Feedback.Positive) {
					players[i].info.pos_weight++;
				}
				players[i].info.weight++;
			}
		} else {
			
			
			for(int i=0; i<2; i++) {
				if(players[i].info.weight > threshold && players[i].info.rep > min_rep && f[i] == Feedback.Negative  && players[1-i].info.rep <= max_rep ) {
					players[1-i].info.pos_weight = 0;
					players[1-i].info.weight += 10;
					players[1-i].info.rep = 0;
					return;
				}
			}
			
			for(int i=0; i<2; i++) {
				double temp = 1.0;
				// 
				if(players[1-i].info.pos_weight <= threshold  || (players[i].info.weight >= threshold && players[1-i].info.rep < min_rep)) {
					temp = 0.0;
				}
				if(f[i] == Feedback.Positive) {
					players[i].info.pos_weight += temp;
				}
				players[i].info.weight += temp;
			}
		}
		
		
		for(Player ply: players) {
			if(ply.info.weight == 0) {
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
