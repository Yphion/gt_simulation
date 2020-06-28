package core;

public class PlayerInfo {
	
	static FeedbackSystem fs;
	
	public double rep = fs.getDefault();
	
	public int positive = 0;
	public int total = 0;
	
	public double obj_rep = 1.0;
	public int obj_positive = 0;
	public int obj_total = 0;
	
	public double weight;
	public double pos_weight;
	//public static boolean writeThrough = true;
	
	public PlayerInfo() {
		//def = Math.random();
	}
	
	public double getRep() {
		return rep;
	}
	
	public double getPureRep() {
		if(total == 0) {
			return 1.0;
		}
		return 1.0*positive/total;
		//return 1.0;
		//return 100000.0*positive/(1*(total-positive)+100000*positive);
	}
	
	public void addPositive() {
		obj_positive++;
		obj_total++;
		obj_rep = 1.0*obj_positive/obj_total;
	}
	
	public void addNegative() {
		obj_total++;
		obj_rep = 1.0*obj_positive/obj_total;
	}
	
	public void reset() {
		rep = fs.getDefault();
		positive = 0;
		total = 0;
		obj_rep = 1.0;
		obj_positive = 0;
		obj_total = 0;
		weight = 0;
		pos_weight = 0;
	}
}

