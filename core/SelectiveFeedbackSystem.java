package core;

public class SelectiveFeedbackSystem extends FeedbackSystem {

	double threshold = 0.9;

	@Override
	protected void processFeedback(Player[] players, Feedback[] f) {

//		if (players[0].info.rep >= threshold && players[0].info.weight > 100) { // 0 established
//			if (players[1].info.rep >= threshold && players[1].info.weight > 100) { // 1 established
//				for (int i = 0; i < 2; i++) {
//					if (f[i] == Feedback.Positive) {
//						players[i].info.pos_weight += 1;
//					}
//					players[i].info.weight += 1;
//				}
//			} else { // 1 not established
//				if(f[1]==Feedback.Positive) {
//					players[1].info.pos_weight += 1;
//				}
//				players[1].info.weight += 1;
//			}
//		} else {
//			if (players[1].info.rep >= threshold && players[1].info.weight > 100) { // 1 established
//				if(f[0]==Feedback.Positive) {
//					players[0].info.pos_weight += 1;
//				}
//				players[0].info.weight += 1;
//			} else { // 1 not established
//				for (int i = 0; i < 2; i++) {
//					if (f[i] == Feedback.Positive) {
//						players[i].info.pos_weight += 1;
//					}
//					players[i].info.weight += 1;
//				}
//			}
//		}
		for(int i=0; i<2; i++) {
			// 
			if(players[1-i].info.rep < threshold && players[1-i].info.weight > 100 ) {
				continue;
			}
			if(f[i]==Feedback.Positive) {
				players[i].info.pos_weight += 1;
			}
			players[i].info.weight += 1;
		}
		for (int i = 0; i < 2; i++) {
			if (players[i].info.weight == 0.0) {
				continue;
			}
			players[i].info.rep = players[i].info.pos_weight / players[i].info.weight;
		}

	}

	@Override
	protected void postprocessFeedback(Player[] players, Feedback[] f) {
		// TODO Auto-generated method stub

	}

}
