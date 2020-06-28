package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

import players.*;

public class RunGame {

	public static void main(String[] args) {
		
		//int[] dist = new int[] {1000,0,0,0};
		int[] dist = new int[] { 220, 460, 170, 150 };
		int rep = 10;
		int m = 5001;

		ArrayList<Player> players = new ArrayList<Player>();


		//double f = 6.5;
		//double f = 0.35;
		PlayerInfo.fs = new TransformedFeedbackSystem(0.35);

		// AC, TR, MD, MO

		// int[] dist = new int[] {1000,0,0,0};
		// 22
		for (int i = 0; i < dist[0]; i++) {
			players.add(new AlwaysCooperate());
		}

//		for (int i = 0; i < 0; i++) {
//			players.add(new SafeCooperate());
//		}

		// 46
		for (int i = 0; i < dist[1]; i++) {
			players.add(new MixedOeco());
		}

//		for (int i = 0; i < 0; i++) {
//			players.add(new HomoOeconomicus());
//		}
		// 17
		for (int i = 0; i < dist[2]; i++) {
			players.add(new TruthfulRandom());
		}

//		for (int i = 0; i < 0; i++) {
//			players.add(new AlwaysDefect());
//		}

//		for (int i = 0; i < 0; i++) {
//			players.add(new EvilDefect());
//		}
		// 15
		for (int i = 0; i < dist[3]; i++) {
			players.add(new MafiaDefect());
		}

//		for (int i = 0; i < 0; i++) {
//			players.add(new AdaptiveCooperate());
//		}
		
		Player[] _p = new Player[1000];
		_p = players.toArray(_p);

		// File ftaxes = new File("taxes.txt");
		File fc_tax = new File("c_tax.txt");
		File fev_tax = new File("ev_tax.txt");
		File fev_scams = new File("ev_scams.txt");
		File fprop_tax = new File("prop_tax.txt");
		File fev_inc = new File("ev_inc.txt");
		File fev_cap = new File("ev_cap.txt");
		File fev_pop = new File("ev_pop.txt");
		File fev_trust = new File("ev_trust.txt");
		File fev_puretrust = new File("ev_puretrust.txt");
		File fev_objtrust = new File("ev_obj_trust.txt");
		File flist_inc = new File("list_inc.txt");
		File flist_cap = new File("list_cap.txt");
		File flist_trust = new File("list_trust.txt");
		
		File fexp_ctax = new File("exp_ctax.txt");
		// ftaxes.delete();
		fc_tax.delete();
		fev_tax.delete();
		fev_scams.delete();
		fprop_tax.delete();
		fev_inc.delete();
		fev_cap.delete();
		fev_pop.delete();
		fev_trust.delete();
		fev_puretrust.delete();
		fev_objtrust.delete();
		flist_inc.delete();
		flist_cap.delete();
		flist_trust.delete();
		fexp_ctax.delete();
		try {
			// ftaxes.createNewFile();
			fc_tax.createNewFile();
			fev_tax.createNewFile();
			fev_scams.createNewFile();
			fprop_tax.createNewFile();
			fev_inc.createNewFile();
			fev_cap.createNewFile();
			fev_pop.createNewFile();
			fev_trust.createNewFile();
			fev_puretrust.createNewFile();
			fev_objtrust.createNewFile();
			flist_inc.createNewFile();
			flist_cap.createNewFile();
			flist_trust.createNewFile();
			fexp_ctax.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int[][] ev_tax = new int[rep][m];
		int[][] ev_scams = new int[rep][m];

		double[][][] prop_tax = new double[rep][4][m];

		double[][][] ev_inc = new double[rep][4][m];
		double[][][] ev_cap = new double[rep][4][m];
		int[][][] ev_pop = new int[rep][4][m];
		double[][][] ev_trust = new double[rep][4][m];
		double[][][] ev_puretrust = new double[rep][4][m];
		double[][][] ev_objtrust = new double[rep][4][m];

		long[][] c_tax = new long[rep][m];

		List<Double>[] list_trust = (ArrayList<Double>[]) new ArrayList[4];
		List<Integer>[] list_inc = (ArrayList<Integer>[]) new ArrayList[4];
		List<Integer>[] list_cap = (ArrayList<Integer>[]) new ArrayList[4];

		for (int i = 0; i < 4; i++) {
			list_trust[i] = new ArrayList<Double>();
			list_inc[i] = new ArrayList<Integer>();
			list_cap[i] = new ArrayList<Integer>();
		}

		for (int i = 1; i < 4; i++) {
			dist[i] += dist[i - 1];
		}

		PrintStream pc_tax, pev_tax, pev_scams, pprop_tax, pev_inc, pev_cap, pev_pop, pev_trust, pev_puretrust,
				pev_objtrust, plist_inc, plist_cap, plist_trust;
		PrintStream pexp_ctax;
		try {
			// ptaxes = new PrintStream(ftaxes);
			// ptaxes.println("Factor\tTax Avg\tTax Stdv\tATax Avg\tATax Stdv");

			pc_tax = new PrintStream(fc_tax);
			pc_tax.println("Cumulative Revenue each Round");
			pc_tax.println("Avg\tStdv");

			pev_tax = new PrintStream(fev_tax);
			pev_tax.println("Revenue each Round");
			pev_tax.println("Avg\tStdv");

			pev_scams = new PrintStream(fev_scams);
			pev_scams.println("Number of Scams over the Genrations");
			pev_scams.println("Avg\tStdv");

			pprop_tax = new PrintStream(fprop_tax);
			pprop_tax.println("Taxes of the Strategies over the Generations");
			pprop_tax.println("Honest\tRational\tRandom\tMalicious\tStdv H\tStdv R\tStdv RND\tStdv BG");

			pev_inc = new PrintStream(fev_inc);
			pev_inc.println("Income of the Strategies over the Generations");
			pev_inc.println("Honest\tRational\tRandom\tMalicious\tStdv H\tStdv R\tStdv RND\tStdv BG");

			pev_cap = new PrintStream(fev_cap);
			pev_cap.println("Capital of the Strategies over the Generations");
			pev_cap.println("Honest\tRational\tRandom\tMalicious\tStdv H\tStdv R\tStdv RND\tStdv BG");

			pev_pop = new PrintStream(fev_pop);
			pev_pop.println("Active Traders of the Strategies over the generations");
			pev_pop.println("Honest\tRational\tRandom\tMalicious\tStdv H\tStdv R\tStdv RND\tStdv BG");

			pev_trust = new PrintStream(fev_trust);
			pev_trust.println("Trust of the Strategies over the Generations");
			pev_trust.println("Honest\tRational\tRandom\tMalicious\tStdv H\tStdv R\tStdv RND\tStdv BG");

			pev_puretrust = new PrintStream(fev_puretrust);
			pev_puretrust.println("Reported Trust of the Strategies over the Generations");
			pev_puretrust.println("Honest\tRational\tRandom\tMalicious\tStdv H\tStdv R\tStdv RND\tStdv BG");

			pev_objtrust = new PrintStream(fev_objtrust);
			pev_objtrust.println("Objective Trust of the Strategies over the Generations");
			pev_objtrust.println("Honest\tRational\tRandom\tMalicious\tStdv H\tStdv R\tStdv RND\tStdv BG");

			plist_inc = new PrintStream(flist_inc);
			plist_inc.println("Incomes of the Strategies at the End of the Simulation");
			plist_inc.println("Honest\tRational\tRandom\tMalicious");

			plist_cap = new PrintStream(flist_cap);
			plist_cap.println("Capital of the Strategies at the End of the Simulation");
			plist_cap.println("Honest\tRational\tRandom\tMalicious");

			plist_trust = new PrintStream(flist_trust);
			plist_trust.println("Trust of the Strategies at the End of the Simulation");
			plist_trust.println("Honest\tRational\tRandom\tMalicious");

			pexp_ctax = new PrintStream(fexp_ctax);
			
			
			// for (double f = 0.0; f <= 2.0; f += 0.1) {
			System.gc();

			// List<Long> list_ctax = new ArrayList<Long>();
			// List<Long> list_tax = new ArrayList<Long>();

			for (int k = 0; k < rep; k++) {
				// long time = System.currentTimeMillis();

				for (Player ply : players) {
					ply.reset();
				}

				// Utils.createFile(players);
				// FeedbackSystem fs = new NoFeedbackSystem();
				 FeedbackSystem fs = new ObjectiveFeedbackSystem();
				// FeedbackSystem fs = new PureFeedbackSystem();
				// FeedbackSystem fs = new TransformedFeedbackSystem(0.35);
				// FeedbackSystem fs = new SelectiveFeedbackSystem();
				// FeedbackSystem fs = new WeightedFeedbackSystem(6.5);
				// FeedbackSystem fs = new VeteranFeedbackSystem(0.9);

				GameInfo gi = new GameInfo(10, 13, 1, 0.0);
				GameManager gm = new GameManager(gi, fs);
				IterationManager im = new RandomIterationManager(players, gm);
				StaticEvolutionManager em = new StaticEvolutionManager(players, im, dist, true, m, _p);
				em.run();
				// list_ctax.add(em.alltime_tax);
				// list_tax.add(em.tax);

				c_tax[k] = em.c_tax;

				ev_tax[k] = em.ev_tax;
				ev_scams[k] = em.ev_scams;

				prop_tax[k] = em.prop_tax;

				ev_inc[k] = em.ev_inc;
				ev_cap[k] = em.ev_cap;
				ev_pop[k] = em.ev_pop;
				ev_trust[k] = em.ev_trust;
				ev_puretrust[k] = em.ev_puretrust;
				ev_objtrust[k] = em.ev_objtrust;

				
				if (k < 10) {
					int j = 0;
					for (int i = 0; i < 4; i++) {
						for (; j < dist[i]; j++) {
							list_cap[i].add(em._p[j].value);
							list_inc[i].add(em._p[j].value - em._p[j].prev_value);
							list_trust[i].add(em._p[j].info.rep);
						}
					}
				}

				// Utils.statisticalAnalysis(players);
				// System.out.println((System.currentTimeMillis()-time)/1000);
			}

			// double[] res1 = Utils.getAvg(list_tax);
			// double[] res2 = Utils.getAvg(list_ctax);

			// ptaxes.println(f+"\t"+res1[0]+"\t"+res1[1]+"\t"+res2[0]+"\t"+res2[1]);
			// ptaxes.flush();

//				fc_tax
//				fev_tax
//				fev_scams
//				fprop_tax
//				fev_inc
//				fev_cap
//				fev_pop
//				fev_trust
//				fev_puretrust
//				fev_objtrust

			
			Utils.print(pc_tax, Utils.getAvg(c_tax));
			Utils.print(pev_tax, Utils.getAvg(ev_tax));
			Utils.print(pev_scams, Utils.getAvg(ev_scams));
			Utils.print(pprop_tax, Utils.getAvg(prop_tax));
			Utils.print(pev_inc, Utils.getAvg(ev_inc));
			Utils.print(pev_cap, Utils.getAvg(ev_cap));
			Utils.print(pev_pop, Utils.getAvg(ev_pop));
			Utils.print(pev_trust, Utils.getAvg(ev_trust));
			Utils.print(pev_puretrust, Utils.getAvg(ev_puretrust));
			Utils.print(pev_objtrust, Utils.getAvg(ev_objtrust));

			Utils.print(plist_cap, list_cap);
			Utils.print(plist_inc, list_inc);
			Utils.print(plist_trust, list_trust);

			for(int i=0; i<rep; i++) {
				pexp_ctax.print(c_tax[i][m-1]+"\t");
			}
			
			// }
			// ptaxes.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println("Simulation over");
	}

}
