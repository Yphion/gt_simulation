package players;
import core.*;

public class AlwaysCooperate extends Player{
	
	public AlwaysCooperate() {
		name = "Always Cooperate";
		identifier = "AC";
	}
	
	private static StrategyMatrix stratMat = new StrategyMatrix(Strategy.Cooperate, Feedback.Positive, Feedback.Negative);
	
	@Override
	public StrategyMatrix getStrategy(GameInfo gi, PlayerInfo info) {
		return ((info.getRep()*gi.gain-gi.invest-gi.tax) > 1)?stratMat:StrategyMatrix.noTrade;
	}
}
