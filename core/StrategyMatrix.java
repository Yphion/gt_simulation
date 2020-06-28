package core;

public class StrategyMatrix {
	Strategy strat;
	Feedback[] f;
	
	public static StrategyMatrix noTrade = new StrategyMatrix(Strategy.NoTrade, Feedback.Positive, Feedback.Negative);
	
	
	public StrategyMatrix(Strategy strat, Feedback f1, Feedback f2) {
		this.strat = strat;
		this.f = new Feedback[]{f1, f2};
	}
	
	public StrategyMatrix(Strategy strat, Feedback[] f) {
		this.strat = strat;
		this.f = f;
	}
}
