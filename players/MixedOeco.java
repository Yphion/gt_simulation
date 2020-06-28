package players;

import core.*;

public class MixedOeco extends Player{
	
	public MixedOeco() {
		name = "Mixed Oeconomicus";
		identifier = "MO";
	}
	
	private static StrategyMatrix stratMat1 = new StrategyMatrix(Strategy.Cooperate, Feedback.Positive, Feedback.Negative);
	private static StrategyMatrix stratMat2 = new StrategyMatrix(Strategy.Defect, Feedback.Positive, Feedback.Negative);
	
	@Override
	public StrategyMatrix getStrategy(GameInfo gi, PlayerInfo info) {

//		if(info.getRep()*gi.gain-gi.invest-gi.tax <= 0) {
//			return stratMat2;
//		}
		
		return (info.getRep()*gi.gain-gi.invest-gi.tax) > 0 ? (Math.random() < 0.05 ? stratMat2 : stratMat1 ) : StrategyMatrix.noTrade;

	}
	
}