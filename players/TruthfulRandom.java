package players;

import core.*;

public class TruthfulRandom extends Player{
	
	public TruthfulRandom() {
		name = "Truthful Random";
		identifier = "R";
	}
	
	private static StrategyMatrix stratMat1 = new StrategyMatrix(Strategy.Cooperate, Feedback.Positive, Feedback.Negative);
	private static StrategyMatrix stratMat2 = new StrategyMatrix(Strategy.Cooperate, Feedback.Negative, Feedback.Positive);
	private static StrategyMatrix stratMat3 = new StrategyMatrix(Strategy.Defect, Feedback.Positive, Feedback.Negative);
	private static StrategyMatrix stratMat4 = new StrategyMatrix(Strategy.Defect, Feedback.Negative, Feedback.Positive);

	double t1 = 0.5;
	double t2 = 0.5;
	double t3 = 0.5;
	
	@Override
	public StrategyMatrix getStrategy(GameInfo gi, PlayerInfo info) {
		return (info.getRep() > 0.3)?(Math.random() < t1 ? (Math.random() < t2 ? stratMat1 : stratMat2 ) : (Math.random() < t3 ? stratMat3 : stratMat4 )) : StrategyMatrix.noTrade;
		//return (info.getRep()*gi.gain-gi.tax > 0)?(Math.random()<0.5? stratMat1 : stratMat2):StrategyMatrix.noTrade;
	}
}
