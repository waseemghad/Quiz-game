/**********************************************************************

 author:  WASEEM HEMAT GHADARI
   date:  01/11/2025
version:  2.0

An intimidating game quiz that doubles or halves the user's prize money

Changes:
- use of arrays
- use of records
- added validation
- refined layout

***********************************************************************/

import java.util.Scanner;

class QuestionData
    {
        String question;
        String user_input;
        String answer;
        boolean answer_status = true; // Initialising
    }


class quiz_game
{
    public static void main (String [] a)
    {
        theGame();
        return;
    }

    // Stores the questions
    // Creates an array of type QuestionData
    public static QuestionData[] questionStore ()
        {
        final int NUM_OF_QUESTIONS = 5;
        QuestionData questionArray[] = new QuestionData[NUM_OF_QUESTIONS];
    
        for (int i=0;i<NUM_OF_QUESTIONS;i++)
            {
                questionArray[i] = new QuestionData();
            }
        
        questionArray[0].question = "What's the capital city of Brazil?";
        questionArray[0].answer = "BRASILIA";

        questionArray[1].question = "What's the capital city of Jamaica?";
        questionArray[1].answer = "KINGSTON";
        
        questionArray[2].question = "What's the capital city of Morocco?";
        questionArray[2].answer = "RABAT";
        
        questionArray[3].question = "What's the capital city of Turkey?";
        questionArray[3].answer = "ANKARA";
        
        questionArray[4].question = "What's the capital city of Turkmenistan? (difficult)";
        questionArray[4].answer = "ASHGABAT";
    
        return questionArray;
        }

    // Prints text
    //
    public static void print (String text)
        {
        System.out.println(text);
        return;
        }

    // Used to ask question
    // Returns user input
    public static String inputString (String question)
        {
        Scanner keyboard = new Scanner(System.in);
        String input;

        print(question);
        input = keyboard.nextLine();

        return input;
        }

    // Collects user's name
    //
    public static String nameAsk ()
        {
        Scanner keyboard = new Scanner(System.in);
        String name;

        print("Welcome.");
        
        name = inputString("May I ask for your name?");

        print("So your name is " + name + "..");
        
        return name;
        }

    // Printed text at beginning, explains the game rules
    //
    public static String gameRules ()
        {
        String playerName;
        String readyString;
        boolean notReady = true; //Initialising
            
        print("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        print("");
            
        playerName = nameAsk();
        
        print("$$ " + playerName + ", welcome to.. $$");
        print("");
        print("     $$$ THE GAME $$$");
        print("");
        print("Allow me to explain the rules.");
        print("");
        print("You start off with £500.");
        print("$ You will be asked a set of 5 questions.");
        print("$ If a question is answered right, the prize money will double,");
        print("$ Whereas if it is answered incorrectly, the prize money will halve.");
        print("$ Type your answers completely in CAPITALS, with no punctuation except for spaces (if required).");
        print("");
    
        while (notReady)
            {
            readyString = inputString(playerName + ", are you READY? (type YES in capitals)");
        
                if (readyString.equals("YES"))
                {
                    notReady = false;
                    
                    print("$$ LET THE GAME BEGIN $$");
                    print("");
                    print("");
                }
                else
                {
                    notReady = true;
    
                    print("please type YES..");
                }
            }
            
        return playerName;
        }

    // Convert double into String
    // Prints money in readable format
    public static String moneyDoubleToString (double dblMoney)
        {
        String stringMoney;
        int intMoney;
        stringMoney = "£" + dblMoney; // double is able to convert into String
    
        if ((dblMoney*10) - (int)(dblMoney*10) == 0) // (int)money always rounds down
        {
            stringMoney = stringMoney + "0"; // 0/1 decimal place values
        }
        else if (dblMoney - (int)dblMoney == 0)
        {
            stringMoney = stringMoney; // 2 decimal place
        }
        else
        {
        dblMoney = dblMoney * 100;
        intMoney = (int)Math.round(dblMoney);
        dblMoney = (double)intMoney / 100;
        
        stringMoney = "£" + (dblMoney);
        }
    
        return stringMoney;
        }

    // If retake = true, causes question to be reasked
    //
    public static int ifRetake (int COUNT, boolean retake)
        {
        if (retake)
        {
            COUNT = COUNT - 1;
        }
        
        return COUNT;
        }

    // Calculates user's new balance for each question
    //
    public static double balanceFind (boolean status, double money_before)
        {
        double money_after;
        
        if (status) // meaning status is true
        {
            money_after = money_before * 2;
        }
        else
        {
            money_after = money_before / 2;
        }
    
        return money_after;
        }
    
    // Displays whether user got answer correct, with new balance
    //
    public static boolean resultDisplay (int COUNT, double balance, QuestionData[] qArray)
        {
        String readeable_balance = moneyDoubleToString(balance);
        boolean retake = false; // Initialising
        String Y_or_N;
        boolean invalid_response = true; // Initialising
        
        print("$ You answered: " + qArray[COUNT].user_input + "..");
        print("");
        System.out.print("$ Your answer is: ");
        
        if (qArray[COUNT].answer_status) // meaning answer_status is true, answer is correct
        {
            print("RIGHT");
        }
        else
        {
            print("WRONG");
        }
        
        print("$$ Your new balance is.. " + readeable_balance);
    
        while ((invalid_response) && (qArray[COUNT].answer_status == false))
        {
            
        print("");
        Y_or_N = inputString("$ Would you like to try again? (Y/N)");
    
        if (Y_or_N.equals("Y"))
        {
            retake = true;
            invalid_response = false;
        }
        else if (Y_or_N.equals("N"))
        {
            retake = false;
            invalid_response = false;
            
            print("$ The correct answer was " + qArray[COUNT].answer);
        }
        else
        {
            print("Please type Y or N (meaning yes or no).");
            invalid_response = true; // so response is valid
        }
    
        }
        
        print("");
        print("");
        
        return retake;
        }

    // Checks whether answer of user is correct
    // returns boolean
    public static QuestionData[] answerStatus (int loop_count, QuestionData[] qArray)
        {
        
        if (qArray[loop_count].user_input.equals(qArray[loop_count].answer))
        {
            qArray[loop_count].answer_status = true;
        }
        else
        {
            qArray[loop_count].answer_status = false;
        }
    
        return qArray;
        }

    // Asks each quesion
    // Calls different methods with for loop
    public static double questionAsk (QuestionData[] questionArray)
        {
        double balance = 500.0;
        boolean retake = true; // Initialising
        
        for (int COUNT = 0; COUNT<5; COUNT++)
            {
                print("$$ QUESTION " + (COUNT + 1) + " $$");
                
                questionArray[COUNT].user_input = inputString("$ " + questionArray[COUNT].question);
                
                questionArray = answerStatus(COUNT, questionArray);
    
                balance = balanceFind(questionArray[COUNT].answer_status, balance);
    
                retake = resultDisplay(COUNT, balance, questionArray);
    
                COUNT = ifRetake(COUNT, retake);
            }
        return balance;
        }
    
    // Finds number of performance points user got out of 5
    // 5 being best
    public static int performanceMarkCalc (QuestionData[] qArray)
        {
        int NUM_OF_QUESTIONS = 5;
        int performance_points = 0;

        for (int i=0;i<NUM_OF_QUESTIONS;i++)
            {
                if (qArray[i].answer_status)
                    {
                        performance_points = performance_points + 1;
                    }
            }
        return performance_points;
        }

    // Determines user's performance using their performance mark
    //
    public static String performanceFind (int performance_marks)
        {
        String perf = ""; // Initialise

        if (performance_marks == 5) // 5/5
        {
            perf = "OUTSTANDING";
        }
        else if (performance_marks == 4) // 4/5
        {
            perf = "GREAT";
        }
        else if (performance_marks == 3) // 3/5
        {
            perf = "SATISFACTORY";
        }
        else if (performance_marks == 2) // 2/5
        {
            perf = "NOT GOOD";
        }
        else if (performance_marks == 1) // 1/5
        {
            perf = "BAD";
        }
        else
        {
            perf = "PITIFUL";
        }

        return perf;
        }

    // Displays overall results of user's performance
    //
    public static void gameEnd (int performance_marks, String name, String balance, String performance)
        {
        print("..");
        print("..");
        print("$ " + name + "..");
        print("$ You got " + performance_marks + " out of 5 questions correct.");
        print("$ Your final balance is " + balance + ".");
        print("");

        print("You performance is..  " + performance);

        print("");
        
        if (performance_marks >=3)
        {
            print("$ " + name + ", you have done well..");
            print("$ THE GAME applauds you.");
        }
        else
        {
            print("$ " + name + ", the shadows whisper of your faliure.");
            print("$ THE GAME remembers those who fall short..");
        }



        print("");
        print("$ Thank you for playing..");
        print("");
        print("     $$$ THE GAME $$$");
        print("");
        print("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        
        return;
        }

    // Main method
    //
    public static void theGame ()
        {
        QuestionData[] questionArray = questionStore();
        String user_name;
        String end_balance;
        int performance_points = 0; // Initialise
        String performance;
    
        user_name = gameRules();
        end_balance = moneyDoubleToString(questionAsk(questionArray));
        performance_points = performanceMarkCalc(questionArray);
        performance = performanceFind(performance_points);
        gameEnd(performance_points, user_name, end_balance, performance);
    
        return;
        }
      
}// END class game