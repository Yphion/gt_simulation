package core;

public class ObjectiveFeedbackSystem extends FeedbackSystem{

	@Override
	protected void processFeedback(Player[] players, Feedback[] f) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void postprocessFeedback(Player[] players, Feedback[] f) {
		for(Player ply : players) {
			ply.info.rep = ply.info.obj_rep; 
		}
		
	}

}
