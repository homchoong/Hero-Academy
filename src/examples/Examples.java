package examples;

import ai.AI;
import ai.RandomAI;
import ai.evaluation.HeuristicEvaluator;
import ai.evaluation.RolloutEvaluator;
import ai.mcts.LGRP;
import ai.mcts.Mcts;
import ai.mcts.mctsrave;
import ai.util.RAND_METHOD;
import model.DECK_SIZE;
import game.Game;
import game.GameArguments;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Examples {

	public static void main(String[] args) {


		//humanVsHuman();
		humanVsAI();

		//noGfx();
		
	}

	private static void noGfx() {
		
		AI p1 = new RandomAI(RAND_METHOD.BRUTE);
		AI p2 = new RandomAI(RAND_METHOD.BRUTE);
		
		GameArguments gameArgs = new GameArguments(true, p1, p2, "a", DECK_SIZE.STANDARD);
		gameArgs.gfx = false; 
		Game game = new Game(null, gameArgs);

		int runs = 1;

		game.run();
		System.out.print(game.state.getWinner());

		
	}

	private static void humanVsAI() {
		List<Double> result1 = null;
		List<Double> result2 = null;
		int budget = 1; // 4 sec for AI's
		
		AI p1 =  new Mcts(budget, new RolloutEvaluator(1, 1, new RandomAI(RAND_METHOD.TREE), new HeuristicEvaluator(false)));
		//AI p2 = new RandomAI(RAND_METHOD.BRUTE);
		//AI p2 = new GreedyActionAI(new HeuristicEvaluator(false));
		//AI p2 = new GreedyTurnAI(new HeuristicEvaluator(false), budget);
		AI p2 = new LGRP(budget, new RolloutEvaluator(1, 1, new RandomAI(RAND_METHOD.TREE), new HeuristicEvaluator(false)));
		//AI p2 = new OnlineIslandEvolution(true, 100, 0.1, 0.5, budget, new HeuristicEvaluator(false));

		GameArguments gameArgs = new GameArguments(true, p1, p2, "a", DECK_SIZE.STANDARD);
		gameArgs.budget = budget; 
		Game game = new Game(null, gameArgs);
		
		game.run();

		result1 = ((Mcts) p1).getResults();
		result2 = ((LGRP) p2).getResults();


//		try {
//			File myObj = new File("filename1.txt");
//			if (myObj.createNewFile()) {
//				System.out.println("File created: " + myObj.getName());
//			} else {
//				System.out.println("File already exists.");
//			}
//		} catch (IOException e) {
//			System.out.println("An error occurred.");
//			e.printStackTrace();
//		}
//		try {
//			FileWriter myWriter = new FileWriter("filename1.txt");
//			for(int i = 0; i < result1.size();i++){
//				myWriter.write("mcts " + result1.get(i));
//			}
//			for(int j = 0; j < result2.size();j++){
//				myWriter.write("lgrp " + result2.get(j));
//			}
//			myWriter.close();
//			System.out.println("Successfully wrote to the file.");
//		} catch (IOException e) {
//			System.out.println("An error occurred.");
//			e.printStackTrace();
//		}
	}





	private static void humanVsHuman() {
		
		AI p1 = null;
		AI p2 = null;

		Game game = new Game(null, new GameArguments(true, p1, p2, "a", DECK_SIZE.STANDARD));
		game.run();
		
	}

}
