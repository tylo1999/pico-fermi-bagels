/*
* Bagels.java
* Author: Anthony Logan Bryant
* Submission Date: 9/14/2017
*
* This program will generate a random number based on how many the user wants. Then it will prompt the user for a guess.
* The program will take that guess and return one of three words that will give them a clue on how close they were to getting the number.
* The rules of the game determine which word is printed. Once the number is guessed, the user can either try again, reset the game, or quit.
* 
* 
* Statement of Academic Honesty:
*
* The following code represents my own work. I have neither
* received nor given inappropriate assistance. I have not copied
* or modified code from any source other than the course webpage
* or the course textbook. I recognize that any unauthorized
* assistance or plagiarism will be handled in accordance with
* the University of Georgia's Academic Honesty Policy and the
* policies of this course. I recognize that my work is based
* on an assignment created by the Department of Computer
* Science at the University of Georgia. Any publishing
* or posting of source code for this project is strictly
* prohibited unless you have written consent from the Department
* of Computer Science at the University of Georgia.
*/
import java.util.Scanner;

public class Bagels {
	public static void main(String[] args) {
		//initializing variables
		Scanner keyboard = new Scanner(System.in);
		boolean systemRunning = true;
		boolean gameRunning = true;
		Engine engine = new Engine();
		int gameNumber = 1;
		boolean answer = false;
		int guessNumber = 0;
		System.out.println("Welcome!");
		//returns to this loop if game is reset
		while(systemRunning) {
			Player currentPlayer = new Player();
			answer = false;
			//Store number of Digits and player's name
			
			System.out.print("Enter the number of digits to use: ");
			int digits = keyboard.nextInt();
			engine.setNumDigits(digits);
			keyboard.nextLine();
			System.out.print("Enter the player's name: ");
			String playerName = keyboard.nextLine();
			System.out.println("");
			currentPlayer.setName(playerName);
			gameNumber = 1;
			//While loop that begins game
			while(gameRunning) {
				System.out.println("Starting game # " + gameNumber + ".");
				engine.generateNewSecret();
				int [] secret = engine.getSecretNumber();
				//asks user for input until number is guessed.
				while(answer == false) {
					String guess = currentPlayer.askForGuess();
					guessNumber++;
					int [] numArray = engine.convertNumToDigitArray(guess);
					answer = Validator.validateGuess(secret, numArray, digits);
				}
				// If number is guessed correctly
				if(answer = true) {
					//Prints Statistics
					System.out.println("Congratulations! You won in " + guessNumber + " moves.");
					System.out.println("");
					currentPlayer.updateStats(guessNumber);
					System.out.println("Games completed: " + currentPlayer.getGamesCompleted());
					System.out.println("Number of Digits: " + engine.getNumDigits());
					System.out.println("Fastest Win: " + currentPlayer.getFastestWin() + " guesses");
					//prompts user for input
					System.out.println("p - Play again");
					System.out.println("r - Reset Game");
					System.out.println("q - Quit");

					System.out.println("");
					System.out.print("What would you like to do? ");
					String command = keyboard.next();
					System.out.println("");
					//if else statement that determines user input
					if (command.equalsIgnoreCase("p")) {
						guessNumber = 0;
						answer = false;
						gameNumber++;
						continue;
					} else if (command.equalsIgnoreCase("r")) {

						guessNumber = 0;
						break;
					} else if (command.equalsIgnoreCase("q")) {
						System.out.println("Goodbye!");
						System.exit(0);
					} else {
						System.out.println("Invalid Input. Quitting Game...");
						System.exit(0);
					}
					answer = false;
				}
			} 
		}		
	}
}
