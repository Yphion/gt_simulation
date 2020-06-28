package core;

public class WeightedFeedbackSystem extends FeedbackSystem {

	double min = 0.001;
	double pow = 5;
	
	public WeightedFeedbackSystem(double f) {
		super();
		pow = f;
	}

	@Override
	protected void processFeedback(Player[] players, Feedback[] f) {
		for(int i=0; i<2; i++) {
			if(f[i]==Feedback.Positive) {
				players[i].info.pos_weight += Math.max(Math.pow(players[1-i].info.rep,pow),min);
				
			}
			players[i].info.weight += Math.max(Math.pow(players[1-i].info.rep,pow),min);
		}
		for(int i=0; i<2; i++) {
			players[i].info.rep = players[i].info.pos_weight/players[i].info.weight;
		}
	}

	public double getDefault() {
		return 1.0;
	}
	
	@Override
	protected void postprocessFeedback(Player[] players, Feedback[] f) {
		// TODO Auto-generated method stub
		
	}
	
}
