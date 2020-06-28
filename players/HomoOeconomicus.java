package players;

import core.*;

public class HomoOeconomicus extends Player{
	
	public HomoOeconomicus() {
		name = "Homo Oeconomicus";
		identifier = "HO";
	}
	
	private StrategyMatrix stratMat = new StrategyMatrix(Strategy.Cooperate, Feedback.Positive, Feedback.Negative);
	
	@Override
	public StrategyMatrix getStrategy(GameInfo gi, PlayerInfo info) {
		return (info.getRep()*gi.gain-gi.invest-gi.tax) > 0 ? stratMat : StrategyMatrix.noTrade;
	}
	
}