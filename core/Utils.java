package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

import javax.swing.event.ListSelectionEvent;

public class Utils {
	
	public static void printPlayers(List<Player> players) {
		HashMap<String,Integer> map = getHashMap(players);
		for(HashMap.Entry<String,Integer> e : map.entrySet()) {
			System.out.print(e.getKey()+": "+e.getValue()+"; ");
		}
		System.out.println();
	}
	
	static PrintStream p,p1,p2,p3,p4,p5,p6,p7,p8;
	static String[] order;
	
	/**
	 * 
	 * @param players List of players
	 * @exception SecurityException If a security manager exists and denies write access.
	 * 
	 */
	public static void createFile(List<Player> players) {
		File f = new File("evolution.txt");
		File f1 = new File("money_ev.txt");
		File f2 = new File("trust_ev.txt");
		File f3 = new File("enterprise_ev.txt");
		File f4 = new File("trade_ev.txt");
		File f5 = new File("scammed.txt");
		File f6 = new File("income_ev.txt");
		File f7 = new File("pure_trust_ev.txt");
		File f8 = new File("happiness.txt");
		f.delete();
		f1.delete();
		f2.delete();
		f3.delete();
		f4.delete();
		f5.delete();
		f6.delete();
		f7.delete();
		f8.delete();
		try {
			f.createNewFile();
			f1.createNewFile();
			f2.createNewFile();
			f3.createNewFile();
			f4.createNewFile();
			f5.createNewFile();
			f6.createNewFile();
			f7.createNewFile();
			f8.createNewFile();
		} catch (IOException e1) {
		}
		try {
			p = new PrintStream(f);
			HashMap<String,Integer> map = getHashMap(players);
			
			order = (String[])(map.keySet().toArray(new String[0]));
			Arrays.sort(order);
			p.println(String.join("\t",order));
			
			p1 = new PrintStream(f1);
			p1.println(String.join("\t",order));
			
			p2 = new PrintStream(f2);
			p2.println(String.join("\t",order));
			
			p3 = new PrintStream(f3);
			p3.println("tax/gen\tsucc/gen");
			
			p4 = new PrintStream(f4);
			p4.println(String.join("\t",order));
			
			p5 = new PrintStream(f5);
			p5.println(String.join("\t",order));
			
			p6 = new PrintStream(f6);
			p6.println(String.join("\t",order));
			
			p7 = new PrintStream(f7);
			p7.println(String.join("\t",order));
			
			p8 = new PrintStream(f8);
			p8.println(String.join("\t",order));
			
			addData(players);
			addEvoData(players, new ArrayList<Player>(), new EvolutionManager(players, null));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static HashMap<String, Integer> getHashMap(List<Player> players){
		HashMap<String,Integer> map = new HashMap<String, Integer>();
		for(int i=0; i<players.size(); i++) {
			String s = players.get(i).identifier;
			if(!map.containsKey(s)) {
				map.put(s, 1);
			} else {
				map.put(s, map.get(s)+1);
			}
		}
		return map;
	}
	
	
	public static void addData(List<Player> players) {
		HashMap<String,Integer> map = getHashMap(players);
		for(int i=0; i<order.length; i++) {
			p.print(map.getOrDefault(order[i], 0)+"\t");
		}
		p.println();
		
		
		for(int i=0; i<order.length; i++) {
			double trust = 0;
			double ptrust = 0;
			int n = 0;
			int trade = 0;
			int profit = 0;
			int scammed = 0;
			int income = 0;
			String s = order[i];
			for(Player ply : players) {
				if(ply.identifier.equals(s)) {
					trust += ply.info.getRep();
					ptrust += ply.info.getPureRep();
					trade += ply.trades;
					profit += ply.profit;
					scammed += ply.scammed;
					income += (ply.value-ply.prev_value);
					n++;
				}
			}
			
			p2.print((n<3?"#N/A":trust/n)+"\t");
			p4.print((n==0?0:trade/n)+"\t");
			p5.print((n==0?0:scammed/n)+"\t");
			p6.print((n==0?0:income/n)+"\t");
			p7.print((n<3?"#N/A":ptrust/n)+"\t");
			p8.print((trade<3?"#N/A":1.0*profit/trade)+"\t");
		}
		
		p2.println();
		p4.println();
		p5.println();
		p6.println();
		p7.println();
		p8.println();
	}
	
	public static void addEvoData(List<Player> players, List<Player> trash, EvolutionManager em) {
		
		ArrayList<Player> allplayers = new ArrayList<Player>();
		allplayers.addAll(players);
		allplayers.addAll(trash);
		
		int val = 0;
		double trust = 0;
		int n = 0;
		for(int i=0; i<order.length; i++) {
			String s = order[i];
			for(Player ply : allplayers) {
				if(ply.identifier.equals(s)) {
					val += ply.value;
					trust += ply.info.getRep();
					n++;
				}
			}
			p1.print((n==0?0:val/n)+"\t");
			
			//p2.print((n==0?0:trust/n)+"\t");
		}
		p1.println();
		
		//p2.println();
		
		
		
		p3.print(em.tax+"\t");
		p3.print(em.success);
		p3.println();
		
	}
	
	public static void statisticalAnalysis(List<Player> players) {
		File f = new File("stats.txt");
		File f1 = new File("stats_inc.txt");
		f.delete();
		f1.delete();
		try {
			f.createNewFile();
			f1.createNewFile();
		} catch (IOException e1) {
		}
		try {
			PrintStream prt = new PrintStream(f);
			PrintStream prt1 = new PrintStream(f1);
			for(String s : order) {
				List<Integer> vals = new ArrayList<Integer>();
				List<Integer> incs = new ArrayList<Integer>();
				for(Player ply : players) {
					if(ply.identifier.equals(s)) {
						vals.add(ply.value);
						incs.add(ply.value-ply.prev_value);
					}
				}
				Collections.sort(vals);
				Collections.sort(incs);
				int n = vals.size();
				if(n == 0) {
					prt.println(s+"\t0\t0\t0\t0\t0");
					prt1.println(s+"\t0\t0\t0\t0\t0");
				} else {
					prt.println(s+"\t"+vals.get(0)+"\t"+(vals.get(n/4)-vals.get(0))+"\t"+(vals.get(n/2)-vals.get(n/4))+"\t"+(vals.get(3*n/4)-vals.get(n/2))+"\t"+(vals.get(n-1)-vals.get(3*n/4)));
					prt1.println(s+"\t"+incs.get(0)+"\t"+(incs.get(n/4)-incs.get(0))+"\t"+(incs.get(n/2)-incs.get(n/4))+"\t"+(incs.get(3*n/4)-incs.get(n/2))+"\t"+(incs.get(n-1)-incs.get(3*n/4)));
				}
				
			}
			prt.close();
			prt1.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static double[] getAvg(List<Long> list) {
		
		if(list.size() == 0) {
			return null;
		}
		
		long sum = 0;
		int n = 0;
		
		for(Long i : list) {
			sum += i;
			n++;
		}
		
		double avg = 1.0*sum/n;
		
		double sq = 0;
		for(Long i : list) {
			sq += (i-avg)*(i-avg);
		}
		
		double sdv = Math.sqrt(1.0*sq/(n-1));
		
		return new double[] {avg, sdv};
	}
	
	public static double[][] getAvg(long[][] arr){
		int rep = arr.length;
		int m = arr[0].length;
		double res[][] = new double[m][2];
		
		for(int i=0; i<m; i++) {
			double avg = 0.0;
			for(int k=0; k<rep; k++) {
				avg += arr[k][i];
			}
			avg /= rep;
			
			double stdv = 0.0;
			for(int k=0; k<rep; k++) {
				stdv += (arr[k][i] - avg)*(arr[k][i] - avg);
			}
			
			stdv = Math.sqrt(stdv/(rep-1));
			res[i][0] = avg;
			res[i][1] = stdv;
		}
		
		return res;
	}
	
	public static double[][] getAvg(int[][] arr){
		int rep = arr.length;
		int m = arr[0].length;
		double res[][] = new double[m][2];
		
		for(int i=0; i<m; i++) {
			double avg = 0.0;
			for(int k=0; k<rep; k++) {
				avg += arr[k][i];
			}
			avg /= rep;
			
			double stdv = 0.0;
			for(int k=0; k<rep; k++) {
				stdv += (arr[k][i] - avg)*(arr[k][i] - avg);
			}
			
			stdv = Math.sqrt(stdv/(rep-1));
			res[i][0] = avg;
			res[i][1] = stdv;
		}
		
		return res;
	}
	
	public static double[][][] getAvg(int[][][] arr){
		int rep = arr.length;
		int m = arr[0][0].length;
		double res[][][] = new double[4][m][2];
		
		for(int i=0; i<m; i++) {
			for(int j=0; j<4; j++) {
				double avg = 0.0;
				for(int k=0; k<rep; k++) {
					avg += arr[k][j][i];
				}
				avg /= rep;
				
				double stdv = 0.0;
				for(int k=0; k<rep; k++) {
					stdv += (arr[k][j][i] - avg)*(arr[k][j][i] - avg);
				}
				
				stdv = Math.sqrt(stdv/(rep-1));
				res[j][i][0] = avg;
				res[j][i][1] = stdv;
			}
		}
		
		return res;
	}
	
	public static double[][][] getAvg(double[][][] arr){
		int rep = arr.length;
		int m = arr[0][0].length;
		double res[][][] = new double[4][m][2];
		
		for(int i=0; i<m; i++) {
			for(int j=0; j<4; j++) {
				double avg = 0.0;
				for(int k=0; k<rep; k++) {
					avg += arr[k][j][i];
				}
				avg /= rep;
				
				double stdv = 0.0;
				for(int k=0; k<rep; k++) {
					stdv += (arr[k][j][i] - avg)*(arr[k][j][i] - avg);
				}
				
				stdv = Math.sqrt(stdv/(rep-1));
				res[j][i][0] = avg;
				res[j][i][1] = stdv;
			}
		}
		
		return res;
	}
	
	public static void print(PrintStream p, double[][][] arr) {
		int m = arr[0].length;
		for(int i=0; i<m; i++) {
			for(int j=0; j<2; j++) {
				for(int k=0; k<4; k++) {
					p.print(arr[k][i][j]+"\t");
				}
			}
			p.println();
		}
		
	}
	
	public static void print(PrintStream p, double[][] arr) {
		int m = arr.length;
		for(int i=0; i<m; i++) {
			for(int j=0; j<2; j++) {
				p.print(arr[i][j]+"\t");
			}
			p.println();
		}
	}
	
	public static void print(PrintStream p, List[] list) {
		
		int empty = 0;
		int index = 0;
		while(empty < 4) {
			empty = 0;
			for(int i=0; i<4; i++) {
				if(list[i].size() <= index) {
					empty++;
					p.print("\t");
				} else {
					p.print(list[i].get(index)+"\t");
				}
			}
			p.println();
			index++;
		}
	}
}
