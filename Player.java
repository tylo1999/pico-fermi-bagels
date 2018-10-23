import java.util.Scanner;

public class Player {
	//private instance varibales
	private String name= "";
	private int fastestWin = 0;
	private int gamesCompleted = 0;
	private Scanner keyboard = new Scanner(System.in);
	
	//sets players name
	public void setName(String name) {
		this.name = name;
	}
	//returns players name
	public String getName() {
		return name;
	}
	//asks user for a guess
	public String askForGuess() {
		System.out.print("Enter your guess: ");
		String guess = keyboard.next();
		return guess;
	}
	//returns fastest number of moves
	public int getFastestWin() {
		return fastestWin;
	}
	//returns number of games completed
	public int getGamesCompleted() {
		return gamesCompleted;
	}
	//updates the users stats based on number of games and fastest win
	public void updateStats(int numberOfMovesTakenToWin) {
		System.out.println("Statistics for " + name + ":");
		gamesCompleted++;
		if(gamesCompleted > 1) {
		if(numberOfMovesTakenToWin < fastestWin) {
			fastestWin = numberOfMovesTakenToWin;
		} 
		} else {
			fastestWin = numberOfMovesTakenToWin;
		}
	}
}
