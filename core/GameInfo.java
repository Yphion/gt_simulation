package core;

public class GameInfo {
	public int invest;
	public int gain;
	public double f;
	public int tax;
	public double r;

	
	/**
	 * The GameInfo describes the relevant values in the game
	 * 
	 * @param val Value that players can invest
	 * @param win Ratio at which the value is multiplied
	 * @param f Error rate at which Cooperate and Defect are swapped
	 */
	public GameInfo(int invest, int gain, int tax, double f) {
		this.invest = invest;
		this.gain = gain;
		this.tax = tax;
		this.f = f;
		this.r = 1.0*gain/invest;
		
	}
	
	public GameInfo(int invest, int gain, int tax) {
		this.invest = invest;
		this.gain = gain;
		this.tax = tax;
		this.f = 0;
		this.r = 1.0*gain/invest;
	}
}
