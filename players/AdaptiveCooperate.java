package players;

import core.*;

public class AdaptiveCooperate extends Player{
	
	public AdaptiveCooperate() {
		name = "Adaptive Cooperate";
		identifier = "AdC";
	}
	
	private StrategyMatrix stratMat1 = new StrategyMatrix(Strategy.Cooperate, Feedback.Positive, Feedback.Negative);
	private StrategyMatrix stratMat2 = new StrategyMatrix(Strategy.Cooperate, Feedback.Negative, Feedback.Positive);
	
	@Override
	public StrategyMatrix getStrategy(GameInfo gi, PlayerInfo info) {
		if(this.info.getRep() < (1.0/gi.r)) {
			return info.getRep() < (1.0/gi.r) ? stratMat2 : StrategyMatrix.noTrade;
		}
		return info.getRep() > (1.0/gi.r) ? stratMat1 : StrategyMatrix.noTrade;
	}
	
}