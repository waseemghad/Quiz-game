/****************************************************************

author: WASEEM HEMAT GHADARI
date: 30/09/2025
version: 1.0

An interactive quiz that doubles or halves the user's prize money

*****************************************************************/

import java.util.Scanner; // Done to make scanner available

class quiz_game
{
    public static void main (String [] a) //The full game
    {
        theGame();

        return;
    }
    public static void gameRules () // Printed text at beginning, explains the game rules
    {
        String playerName;
        playerName = nameAsk();
        
        System.out.println("$$ " + playerName + ", welcome to.. $$");
        System.out.println("     $$$ THE QUIZ $$$");
        System.out.println("Allow me to explain the rules.");
        System.out.println("");
        System.out.println("You start off with £500.00.");
        System.out.println("$ You will be asked a set of 5 questions.");
        System.out.println("$ If a question is answered right, the prize money will double,");
        System.out.println("$ Whereas if it is answered incorrectly, the prize money will halve.");
        System.out.println("$ Type your answers completely in CAPITALS, with no punctuation except for spaces (if required).");
        System.out.println("");
        
        return;
    }

    public static String nameAsk () //System that asks user's name and returns the name.
    {
        Scanner keyboard = new Scanner(System.in);
        String name;

        System.out.println("Welcome.");
        System.out.println("May I ask for your name?");
        
        name = keyboard.nextLine();

        System.out.println("So your name is " + name + "..");
        return name;
    }
    
    public static String returnAnswer (String question) // Function that stores and returns user's answers
    {
        Scanner keyboard = new Scanner(System.in);
        String userAnswer;

        System.out.println(question);
        userAnswer = keyboard.nextLine();
        
        return userAnswer;
    }

    public static void question1 ()
    {
        String answer;
        double balance = 500.00;

        System.out.println("Your balance is: £" + balance);
        System.out.println("");
        
        System.out.println("$$ QUESTION 1 $$");
        answer = returnAnswer("What's the weather like today?");

        System.out.println("You answered: " + answer);
        System.out.println("The answer is SUNNY.");
        System.out.println("");

        System.out.println("Your balance is now £" + (balance/2));
        System.out.println("");
        
        return;

    }

    public static void question2 ()
    {
        String answer;
        double balance = 250.00;

        System.out.println("$$ QUESTION 2 $$");
        answer = returnAnswer("What's the weather like today?");

        System.out.println("You answered: " + answer);
        System.out.println("The answer is STORMY.");
        System.out.println("");
        
        System.out.println("Your balance is now £" + balance/2);
        System.out.println("");

        return;
    }

    public static void question3 ()
    {
        String answer;
        double balance = 125.00;

        System.out.println("$$ QUESTION 3 $$");
        answer = returnAnswer("What's the weather like today?");

        System.out.println("You answered: " + answer);
        System.out.println("The answer is RAINY.");
        System.out.println("");
        
        System.out.println("Your balance is now £" + balance/2);
        System.out.println("");
        
        return;
    }

    public static void question4 ()
    {
        String answer;
        double balance = 62.50;

        System.out.println("$$ QUESTION 4 $$");
        answer = returnAnswer("What's the weather like today?");

        System.out.println("You answered: " + answer);
        System.out.println("The answer is CLOUDY.");
        System.out.println("");
        
        System.out.println("Your balance is now £" + balance/2);
        System.out.println("");
        
        return;
    }

    public static void question5 ()
    {
        String answer;
        double balance = 31.25;

        System.out.println("$$ QUESTION 5 $$");
        answer = returnAnswer("What's the weather like today?");

        System.out.println("You answered: " + answer);
        System.out.println("The answer is FOGGY.");
        System.out.println("");
        
        System.out.println("Your balance is now £" + balance/2);
        System.out.println("");
        
        return;
    }


    public static void theGame ()
    {
        gameRules();
        question1();
        question2();
        question3();
        question4();
        question5();
    }

    
}// END class quiz