package players;
import core.*;

public class MafiaDefect extends Player{

	public MafiaDefect() {
		name = "Mafia Defect";
		identifier = "MD";
	}
	
	private static StrategyMatrix stratMat = new StrategyMatrix(Strategy.Defect, Feedback.Negative, Feedback.Positive);
	
	@Override
	public StrategyMatrix getStrategy(GameInfo gi, PlayerInfo info) {
		return stratMat;
	}
	
}
