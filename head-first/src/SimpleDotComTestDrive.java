import java.util.ArrayList;

public class SimpleDotComTestDrive {

	public static void main(String[] args) {
		
		//make new dot com object called dot
		SimpleDotCom dot = new SimpleDotCom();
		
		//fill the object with cell locations
		ArrayList<String> locations = new ArrayList<String>();
		locations.add("2");
		locations.add("3");
		locations.add("4");
		
		dot.setLocationCells(locations);
		
		//make a user guess
		String userGuess = "2";
		
		//check results of that guess
		String result = dot.checkYourself(userGuess);
		
		System.out.println(result);

	}

}
