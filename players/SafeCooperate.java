package players;

import core.*;

public class SafeCooperate extends Player{
	
	public SafeCooperate() {
		name = "Safe Cooperate";
		identifier = "SC";
	}
	
	private StrategyMatrix stratMat = new StrategyMatrix(Strategy.Cooperate, Feedback.Positive, Feedback.Negative);
	
	@Override
	public StrategyMatrix getStrategy(GameInfo gi, PlayerInfo info) {
		return info.getRep() == 1.0 ? stratMat : StrategyMatrix.noTrade;
	}
	
}
