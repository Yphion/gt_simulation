package core;

public class TransformedFeedbackSystem extends FeedbackSystem {
	
	double factor = 0.0;
	
	public TransformedFeedbackSystem(double f) {
		factor = f;
	}
	
	@Override
	protected void processFeedback(Player[] players, Feedback[] f) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void postprocessFeedback(Player[] players, Feedback[] f) {
		for(Player p : players) {
			int pos = p.info.positive;
			int tot = p.info.total;
			int neg = tot-pos;
			//double factor = 4.5;
			//p.info.rep = Math.min(1.0, factor*pos/tot);
			p.info.rep = Math.min(1.0, 1.0*pos/tot+factor);
			//p.info.rep = factor*pos/(factor*pos+neg);
		}
	}

}
