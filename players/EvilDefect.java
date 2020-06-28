package players;
import core.*;

public class EvilDefect extends Player{

	public EvilDefect() {
		name = "Evil Defect";
		identifier = "ED";
	}
	
	private StrategyMatrix stratMat = new StrategyMatrix(Strategy.Defect, Feedback.Negative, Feedback.Negative);
	
	@Override
	public StrategyMatrix getStrategy(GameInfo gi, PlayerInfo info) {
		return stratMat;
	}
	
}
