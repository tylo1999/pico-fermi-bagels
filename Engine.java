import java.util.Random;

public class Engine {
	//private instance variabls
	private int numDigits = 0;
	private int [] secretNumber;
	private Random randomNumberGenerator = new Random();
	//converts a number as a String into an array
	public int [] convertNumToDigitArray(String number) {
		int [] temp = new int [number.length()];
		
		for(int i = 0; i < number.length(); i++) {
			temp [i] = Character.getNumericValue(number.charAt(i));
		}
		
		return temp;
	}
	//returns number of digits
	public int getNumDigits() {
		return numDigits;
	}
	//returns secret number
	public int [] getSecretNumber() {
		return secretNumber;
	}
	//randomly creates a new number
	public void generateNewSecret() {
		double max = Math.pow(10, numDigits - 1);
		double min = Math.pow(10, numDigits) - 1;
		double number = randomNumberGenerator.nextDouble() *(max-min) + min;
	
		int random = (int) number;
	
		String numArray = Integer.toString(random);
		this.secretNumber = convertNumToDigitArray(numArray);
	}
	//sets the number of digits
	public void setNumDigits(int numDigits) {
		this.numDigits = numDigits;
	}
	//sets the secret number
	public void setSecretNumber(int [] secretNumber) {
		for (int i = 0; i < secretNumber.length; i ++) {
			this.secretNumber[i] = secretNumber[i];
		}
		setNumDigits(secretNumber.length);
	}
}
