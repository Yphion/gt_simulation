package players;
import core.*;

public class AlwaysDefect extends Player{

	public AlwaysDefect() {
		name = "Always Defect";
		identifier = "AD";
	}
	
	private StrategyMatrix stratMat = new StrategyMatrix(Strategy.Defect, Feedback.Positive, Feedback.Negative);
	
	@Override
	public StrategyMatrix getStrategy(GameInfo gi, PlayerInfo info) {
		return stratMat;
	}
	
}
