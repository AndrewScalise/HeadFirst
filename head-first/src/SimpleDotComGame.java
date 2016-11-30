import java.util.ArrayList;

public class SimpleDotComGame {

	public static void main(String[] args) {
		int numOfGuesses = 0;
		
		//get user guess
		GameHelper helper = new GameHelper();
		
		//make dotcom object
		SimpleDotCom theDotCom = new SimpleDotCom();
		
		//random num between 0 and 4 for starting location cell position
		int randNum = (int)(Math.random() * 5);
		
		ArrayList<String> locations = new ArrayList<String>();
		
		locations.add(Integer.toString(randNum));
		locations.add(Integer.toString(randNum+1));
		locations.add(Integer.toString(randNum+2));
		
		theDotCom.setLocationCells(locations);
		
		//tells is dotcom is still alive or not
		boolean isAlive = true;
		
		//play the game until dotcom is dead
		while(isAlive == true){
			
			String guess = helper.getUserInput("enter a number");
			
			String result = theDotCom.checkYourself(guess);
			
			System.out.println(result);
			
			numOfGuesses++;
			
			if(result.equals("kill")){
				isAlive = false;
				
				System.out.println("You took " + numOfGuesses + " guesses");
			}
		}
	}

}
