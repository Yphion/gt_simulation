package core;

public class PureFeedbackSystem extends FeedbackSystem {

	@Override
	public void processFeedback(Player[] players, Feedback[] f) {
		
	}

	@Override
	protected void postprocessFeedback(Player[] players, Feedback[] f) {
		for(Player p : players) {
			p.info.rep = p.info.getPureRep();
		}
		
	}
	
	public double getDefault() {
		return 0.9;
	}

}
