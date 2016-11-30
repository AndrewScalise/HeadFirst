package realDotComGame;

import java.util.*;

public class DotComBust {

	private GameHelper helper = new GameHelper();
	private ArrayList<DotCom> dotComsList = new ArrayList<DotCom>();
	private int numOfGuesses = 0;
	
	private void setUpGame(){
		//first make some dot coms and give them locations
		DotCom one = new DotCom("Pets.com");
		DotCom two = new DotCom("eToys.com");
		DotCom three = new DotCom("Go2.com");
		
		dotComsList.add(one);
		dotComsList.add(two);
		dotComsList.add(three);
		
		//print instructions for user
		System.out.println("Your goal is to sink three dot coms.");
		System.out.println("Pets.com, eToys.com, Go2.com");
		System.out.println("Try to sink them all in the fewest number of guesses");
		
		//repeat with each dotcom in the list
		for(DotCom dotComToSet:dotComsList){
			
			//ask the helper for a dotcom location
			ArrayList<String> newLocation = helper.placeDotCom(3);
			
			//call the setter method to give it the location from the helper
			dotComToSet.setLocationCells(newLocation);
			
		}
	}
	
	private void startPlaying(){
		//while dotcoms still exist
		while(!dotComsList.isEmpty()){
			//get user input
			String userGuess = helper.getUserInput("Enter a guess");
			checkUserGuess(userGuess);
		}
		finishGame();
	}
	
	private void checkUserGuess(String userGuess){
		numOfGuesses++;
		String result = "miss";
		for(DotCom dotComToTest:dotComsList){
			result = dotComToTest.checkYourself(userGuess);
			
			if(result.equals("hit")){
				break;
			}
			
			if(result.equals("kill")){
				dotComsList.remove(dotComToTest);
				break;
			}
		}
		
		System.out.println(result);
	}
	
	private void finishGame(){
		System.out.println("All Dot Coms are dead! Your stock is now worthless.");
		if(numOfGuesses <= 18){
			System.out.println("It only took you " +numOfGuesses + " guesses");
			System.out.println(" You got out before your options sank.");
		}
		else{
			System.out.println("Took you long enough. "+numOfGuesses+ " guesses");
			System.out.println("Fish are dancing with your options");
		}
	}
	
	public static void main(String[] args){
		DotComBust game = new DotComBust();
		game.setUpGame();
		game.startPlaying();
	}
}
