package core;

public abstract class FeedbackSystem {
	/**
	 * 
	 * @param p Players who are giving feedback to each other
	 * @param f Feedbacks that are being received
	 */
	protected abstract void processFeedback(Player[] players, Feedback[] f);
	protected abstract void postprocessFeedback(Player[] players, Feedback[] f);
	public void setFeedback(Player[] players, Feedback[] f) {

		processFeedback(players, f);
		
		for(int i=0; i<2; i++) {
			players[i].info.total++;
			if(f[i] == Feedback.Positive) { 
				players[i].info.positive++;
			} else { 
				
			}
		}
		
		postprocessFeedback(players, f);
	}
	public double getDefault() {
		return 1.0;
	}
}
