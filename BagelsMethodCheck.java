
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
* A class designed to help students in CSCI 1301 at UGA
* during their implementation of the "Bagels" project. This class
* uses reflection to examine the Player & Engine classes and checks 
* that methods are properly declared. 
* Included checks are:
* -method name
* -return type
* -parameters
* An important note: 
* 		This class does not test for correct functionality
* 
* @author MitchWilson
*
*/
public class BagelsMethodCheck {
	
	public static void main(String[] args){
		//TODO check if validator is instantiated?
		
		 		//player methods
		Method askForGuessMethod	 		 = null,
				getNameMethod 		 		 = null,
				getFastestWinMethod 	     = null,
				getGamesCompletedMethod      = null,
				setNameMethod				 = null,
				updateStatsMethod 			 = null,
				
				//engine methods
				convertNumToDigitArrayMethod = null,
				getNumDigitsMethod 			 = null,
				getSecretNumberMethod 		 = null,
				generateNewSecretMethod 	 = null,
				setNumDigitsMethod 			 = null,
				setSecretNumberMethod 		 = null;
		
		//player methods
		//askForGuess()
		try {
			askForGuessMethod = Player.class.getMethod("askForGuess", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}
		
		//getName()
		try {
			getNameMethod = Player.class.getMethod("getName", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}
		
		//getFastestWin()
		try {
			getFastestWinMethod = Player.class.getMethod("getFastestWin", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}
		
		//getGamesCompleted()
		try {
			getGamesCompletedMethod = Player.class.getMethod("getGamesCompleted", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}
		
		//setName(String)
		try {
			setNameMethod = Player.class.getMethod("setName", new Class[] {String.class});
		} catch (NoSuchMethodException e) {}
		
		//updateState(int)
		try {
			updateStatsMethod = Player.class.getMethod("updateStats", new Class[] {int.class});
		} catch (NoSuchMethodException e) {}

		//engine methods
		//convertNumToDigitArray(String)
		try {
			convertNumToDigitArrayMethod = Engine.class.getMethod("convertNumToDigitArray", new Class[] {String.class});
		} catch (NoSuchMethodException e) {}
		
		//getNumDigits()
		try {
			getNumDigitsMethod = Engine.class.getMethod("getNumDigits", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}
		
		//getSecretNumber()
		try {
			getSecretNumberMethod = Engine.class.getMethod("getSecretNumber", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}
		
		//generateNewSecret()
		try {
			generateNewSecretMethod = Engine.class.getMethod("generateNewSecret", (Class<?> []) null);
		} catch (NoSuchMethodException e) {}
		
		//setNumDigits(int)
		try {
			setNumDigitsMethod = Engine.class.getMethod("setNumDigits", new Class[] {int.class});
		} catch (NoSuchMethodException e) {}
		
		//setSecretNumber(int[])
		try {
			setSecretNumberMethod = Engine.class.getMethod("setSecretNumber", new Class[] {int[].class});
		} catch (NoSuchMethodException e) {}

		
		System.out.println("--------------------------------------");
		System.out.println("Player: ");
		
		constructorTest(Player.class);
		
		testMethod("askForGuess()", askForGuessMethod, String.class);
		testMethod("getName()", getNameMethod, String.class);
		testMethod("getFastestWin()", getFastestWinMethod, Integer.TYPE);
		testMethod("getGamesCompleted()", getGamesCompletedMethod, Integer.TYPE);
		testMethod("setName(String)", setNameMethod, Void.TYPE);
		testMethod("updateStats(int)", updateStatsMethod, Void.TYPE);

		System.out.println("--------------------------------------");
		System.out.println("Engine: ");
		
		constructorTest(Engine.class);

		testMethod("convertNumToDigitArray(String)", convertNumToDigitArrayMethod, int[].class);
		testMethod("getNumDigits()", getNumDigitsMethod, Integer.TYPE);
		testMethod("getSecretNumber()", getSecretNumberMethod, int[].class);
		testMethod("generateNewSecret()", generateNewSecretMethod, Void.TYPE);
		testMethod("setNumDigits(int)", setNumDigitsMethod, Void.TYPE);
		testMethod("setSecretNumber(int[])", setSecretNumberMethod, Void.TYPE);
			
	}
	
	/**
	 * Method for checking if the student created any constructors
	 * in a given class
	 * 
	 * The project instructions say that they are not to create any
	 * constructors and that we will use the default constructors
	 * during grading. There are 3 different cases that are checked
	 * by this method:
	 * 	1) There is no default constructor - result: fail
	 *  2) There is a default constructor, but there is also another 
	 *  	constructor - result: warning
	 *  3) There is only a default constructor - result: pass
	 *  
	 *  NOTE: for this implementation, we don't care if the student 
	 *  creates a default constructor, we just care that the default 
	 *  is there
	 *  
	 * @param clazz - the class to be checked
	 */
	private static void constructorTest(Class<?> clazz){
		System.out.print("Constructor: \t\t\t");
		Constructor<?> [] arr = clazz.getDeclaredConstructors();
		boolean moreThan1 = false;
		if (arr.length > 1){
			//there's more than 1 constructor
			moreThan1 = true;
		}
		boolean defaultExists = false;
		for (Constructor<?> c: arr){
			//loop through all constructors, looking for default
			if (c.getParameterCount() == 0)
				defaultExists = true;
		}
		
		if (!defaultExists){
			//if the default doesn't exist
			//we don't really care how many there were
			System.out.println("FAIL: ");
			System.out.println("You should not create any constructors");
		}
		else if (moreThan1 && defaultExists){
			//if there was more than 1 constructor, but the
			//default exists..give warning
			System.out.println("WARNING: ");
			System.out.println("The default constructor exists, but you should not create any others");
		}
		

		else if (!moreThan1 && defaultExists){
			//there was only one & it was the default
			System.out.println("passed");
		}
		System.out.println("\n--------------------------------------");
	}

	/**
     * Method for checking if a method is correctly declared in a Stat class
     * @param methodName - the String representation of the method - only used for printing in the test
     * @param method - the Method object that we are testing. Prints an error if the method is null, 
     * which would result from improper spelling or improper parameters. Prints an error if the method
     * has incorrect return type
     * @param type - the return type of the method being testing
     */
    private static void testMethod(String methodName, Method method, java.lang.reflect.Type type){
        System.out.print(methodName + ":\t" + tabs(methodName)); 
         
        //just for looking good
        if(methodName.length() < 7) System.out.print("\t");
         
        if (method == null) 
            System.out.println("failed\n *incorrect parameters, incorrectly spelled, or non-existent*");
        else if
            (!method.getReturnType().equals(type)) System.out.println("failed\n *incorrect return type*");
        else
            System.out.println("passed");
         
        System.out.println("\n--------------------------------------");
    }
    
    /**
     * Just for helping the output line up and look pretty.
     * Takes a method name as a string and decides how 
     * many tabs to return based on the length of the name
     * @param method - the String name to check
     * @return - tabs based on the method name
     */
    private static String tabs(String method){
    	if (method.equals("askForGuess()") || method.equals("getName()")
    			|| method.equals("getNumDigits()")){
    		return "\t\t";
    		
    	} else if (method.equals("getFastestWin()") || method.equals("getGamesCompleted()")
    			||method.equals("setName(String)") || method.equals("updateStats(int)")
    			|| method.equals("getSecretNumber()") || method.equals("generateNewSecret()")
    			|| method.equals("setNumDigits(int)") || method.equals("setSecretNumber(int[])")){
    		return "\t";
    		
    	} else return "";
    }
}