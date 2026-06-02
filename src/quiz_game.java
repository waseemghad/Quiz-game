/**********************************************************************

 author:  WASEEM HEMAT GHADARI
   date:  30/11/2025
version:  3.0

An intimidating game quiz that doubles or halves the user's prize money

Changes:
- added file io
- system to create and choose games

***********************************************************************/

import java.io.*;
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
    public static void main (String [] a) throws IOException
    {
        theGame();
        return;
    }

    // Creates a QuestionData and recieves the selected question set
    // Stores questions and answers into the QuestionData
    public static QuestionData[] createQuestionData (String[] questionAnswerSet) throws IOException
        {
        final int NUM_OF_QUESTIONS = questionAnswerSet.length / 2;   // file contains question and answer
        QuestionData questionArray[] = new QuestionData[NUM_OF_QUESTIONS];

    
        for (int i=0;i<NUM_OF_QUESTIONS;i++)
            {
                questionArray[i] = new QuestionData();
            }

        for (int i=0;i<NUM_OF_QUESTIONS;i++)
            {
                questionArray[i].question = questionAnswerSet[i];
            }

        for (int i=0;i<NUM_OF_QUESTIONS;i++)
            {
                questionArray[i].answer = questionAnswerSet[i + NUM_OF_QUESTIONS];
            }
    
        return questionArray;
        }

    // Gets question
    //
    public static String getQuestion (QuestionData[] qd, int n)
        {
        return qd[n].question;
        }

    // Gets answer
    //
    public static String getAnswer (QuestionData[] qd, int n)
        {
        return qd[n].answer;
        }

    // Gets user_input
    //
    public static String getUserInput (QuestionData[] qd, int n)
        {
        return qd[n].user_input;
        }

    // Gets answer_status (true/false)
    //
    public static boolean getAnswerStatus (QuestionData[] qd, int n)
        {
        return qd[n].answer_status;
        }

    // Gets the total number of questions
    //
    public static int getNumQuestions (QuestionData[] qd)
        {
        return qd.length;
        }

    // Sets value to user_input
    // without returning it
    public static void setUserInput (QuestionData[] qd, int n, String input)
        {
        qd[n].user_input = input;
        return;
        }

    // Checks whether answer of user is correct
    // sets answer status
    public static void determineAnswerStatus (QuestionData[] qArray, int count)
        {
        
        if (getUserInput(qArray,count).equals(getAnswer(qArray,count)))
        {
            qArray[count].answer_status = true;
        }
        else
        {
            qArray[count].answer_status = false;
        }
        return;
        }

    // Counts correct answers
    // Returns mark, number of correct answers
    public static int performanceMarkCalc (QuestionData[] qArray)
        {
        int NUM_OF_QUESTIONS = qArray.length;
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

    // Uses performance marks to calculate a percentage (integer out of 100)
    //
    public static int performancePercentageCalc (QuestionData[] qArray, int perf_points)
        {
        final double NUM_OF_QUESTIONS = qArray.length;
        
        double outOf100 = ( (double)perf_points / NUM_OF_QUESTIONS ) * 100.0;
        
        return (int)outOf100;
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

    // Asks user if they want to create a game or select a game to play
    //
    public static String askCreateOrPlay ()
        {
        String input;
        
        print("$ Welcome.");
        
        print("$ Would you like to create, or play a  $$$ GAME $$$ ?");
        input = inputString("Type 'CREATE' or 'PLAY' (in CAPITALS)");

        while (! (input.equals("CREATE") || input.equals("PLAY")) )
            {
                input = inputString("$ Please type 'CREATE' or 'PLAY'");
            }

        return input;
        }
    
    // Collects user's name
    //
    public static String nameAsk ()
        {
        String name;
        
        name = inputString("May I ask for your name?");

        print("So your name is " + name + "..");
        
        return name;
        }

    // Printed text at beginning, explains the game rules
    //
    public static String gameRules (int num_questions)
        {
        String playerName;
        String readyString;
        boolean notReady = true; //Initialising

        print("$$ THE GAME has now commenced...");
        print("");
        
        playerName = nameAsk();
        
        print("$$ " + playerName + ", welcome to.. $$");
        print("");
        print("     $$$ THE GAME $$$");
        print("");
        print("Allow me to explain the rules.");
        print("");
        print("You start off with £500.");
        print("$ You will be asked a set of " + num_questions + " questions.");
        print("$ If a question is answered right, the prize money will double,");
        print("$ Whereas if it is answered incorrectly, the prize money will halve.");
        print("$ Type your answers completely in CAPITALS (caps lock ^ will help), with no punctuation except for spaces (if required).");
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
            stringMoney = stringMoney + "0"; /* for 0/1 decimal place values */
        }
        else if ((dblMoney) < 0.005)
        {
            stringMoney = "£0.00"; /* for when >=2 decimal places AND being < 0.005 */
        }                        // (money is still stored elsewhere as a double with absolute value)
        else
        {
            dblMoney = dblMoney * 100;
            intMoney = (int)Math.round(dblMoney);
            dblMoney = (double)intMoney / 100;
            
            stringMoney = "£" + dblMoney; /* for when >=2 decimal places AND being >= 0.005 */
        }
            /* 0.005 and greater can round up to £0.01. less rounds to £0.00. */

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
    
    // Displays user's result, with new balance
    // Gives user option to retake
    public static boolean resultDisplay (int COUNT, double balance, QuestionData[] qArray)
        {
        String readeable_balance = moneyDoubleToString(balance);
        boolean retake = false; // Initialising
        String Y_or_N;
        
        print("$ You answered: " + getUserInput(qArray,COUNT) + "..");
        print("");
        System.out.print("$ Your answer is: ");
        
        if (getAnswerStatus(qArray,COUNT)) // meaning answer is correct
        {
            print("RIGHT");
        }
        else
        {
            print("WRONG");
        }
        
        print("$$ Your new balance is.. " + readeable_balance);
    
        if ( (!getAnswerStatus(qArray,COUNT))
            && (!getAnswer(qArray,COUNT).equals("YES")) && (!getAnswer(qArray,COUNT).equals("NO"))) // Retake or answer show
        {
            print("");
            Y_or_N = inputString("$ Would you like to try again? (Y/N)");
        
                if (Y_or_N.equals("Y"))
                {
                    retake = true;
                }
                else if (Y_or_N.equals("N"))
                {
                    retake = false;
                            
                    print("$ The correct answer was " + getAnswer(qArray,COUNT));
                }
            while (!Y_or_N.equals("Y") && !Y_or_N.equals("N"))
                {
                    Y_or_N = inputString("Please type Y or N (meaning yes or no).");

                    if (Y_or_N.equals("Y"))
                    {
                        retake = true;
                    }
                    else if (Y_or_N.equals("N"))
                    {
                        retake = false;
                            
                        print("$ The correct answer was " + getAnswer(qArray,COUNT));
                    }
                }
        }

        if (!getAnswerStatus(qArray,COUNT) &&
                    ((getAnswer(qArray,COUNT).equals("YES")) || (getAnswer(qArray,COUNT).equals("NO"))) ) // No retake if yes/no question
        {
            print("");
            print("$ The correct answer was " + getAnswer(qArray,COUNT));
        }
        
        print("");
        print(".");
        
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
        final int NUM_OF_QUESTIONS = getNumQuestions(questionArray);
        String input;
        
        for (int qCOUNT=0; qCOUNT<NUM_OF_QUESTIONS; qCOUNT++)
            {
                print("$$ QUESTION " + (qCOUNT + 1) + " $$");
                
                input = inputString("$ " + getQuestion(questionArray,qCOUNT));

                while ( ! (input.equals("YES") || input.equals("NO"))
                      && ( getAnswer(questionArray,qCOUNT).equals("YES") || getAnswer(questionArray,qCOUNT).equals("NO") ) )
                {
                    print("Please type YES or NO.");
                    print("");
                    input = inputString("$ " + getQuestion(questionArray,qCOUNT));
                }
                
                setUserInput(questionArray,qCOUNT,input);
                
                determineAnswerStatus(questionArray,qCOUNT);
    
                balance = balanceFind(getAnswerStatus(questionArray,qCOUNT), balance);
    
                retake = resultDisplay(qCOUNT, balance, questionArray);
    
                qCOUNT = ifRetake(qCOUNT, retake); // Subtracts one from the count if user retakes quesiton
            }
        return balance;
        }

    // Determines user's performance using their performance percentage
    //
    public static String performanceFind (int perf_percentage)
        {
        String perf = ""; // Initialise

        if (perf_percentage >= 80)
        {
            perf = "OUTSTANDING";
        }
        else if (perf_percentage >= 60)
        {
            perf = "GREAT";
        }
        else if (perf_percentage >= 40)
        {
            perf = "SATISFACTORY";
        }
        else if (perf_percentage >= 20)
        {
            perf = "NOT GOOD";
        }
        else if (perf_percentage >= 10)
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
    public static void gameEnd (QuestionData[] qArray, int perf_percentage, int perf_marks, String name, String balance, String performance)
        {
        final int NUM_OF_QUESTIONS = getNumQuestions(qArray);
        
        print(".");
        print("..");
        print("$ " + name + "..");
        print("$ You got " + perf_marks + " out of " + NUM_OF_QUESTIONS + " questions correct.");
        print("$ Your final balance is " + balance + ".");
        print("");

        print("You performance is..  " + performance);

        print("");
        
        if (perf_percentage >= 40)
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
        
        return;
        }

    // Searches an array for a key
    //
    public static boolean searchQSets (String[] array, String key)
        {
        for (int i=0;i<array.length;i++)
            {
                if (array[i].equals(key))
                {
                    return true;
                }
            }
        return false;
        }

    // Displays the question sets
    // Asks user to choose their set
    public static String[] qSetAsk () throws IOException
        {
        String qSetFile = "question_sets.txt";
        BufferedReader counter = new BufferedReader (new FileReader(qSetFile));
        BufferedReader inputStream = new BufferedReader (new FileReader(qSetFile));

        String[] qSets = {""};
        String[] selection = {""};
        String input;
        boolean isInArray = false;
        int count = 0;

        String line = inputStream.readLine();

        while (line != null) // Makes size of array
            {
                count++; // Counts size of options array here
                line = inputStream.readLine();
            }

        qSets = new String[count];

        inputStream = new BufferedReader (new FileReader(qSetFile));

        for (int i=0;i<count;i++) // move question sets from file into array
            {
                line = inputStream.readLine();
                qSets[i] = line;
            }

        print("$ Choose which GAME you would like to proceed with.");
        print("");
                
        for (int i=0;i<qSets.length;i++) // print out options
            {
                if (i == 0)
                {
                    System.out.print(qSets[i]);
                }
                else 
                {
                    System.out.print(", " + qSets[i]);
                }
            }
        input = inputString("");  
        isInArray = searchQSets(qSets, input);

        while (!isInArray) // validator for file selection
            {

                print("");
                print("That file was not found, try again.");
                
                input = inputString("");
                isInArray = searchQSets(qSets, input);
            } // END validator
            
        inputStream = new BufferedReader (new FileReader(input + ".txt"));

        line = inputStream.readLine();
        count = 0;

        while (line != null) // Makes size of array
            {
                count++; // Counts question and answer array size here
                line = inputStream.readLine();
            }

        selection = new String[count];

        inputStream = new BufferedReader (new FileReader(input + ".txt"));

        for (int i=0;i<count;i++) // Move questions and answers from file into array
            {
                line = inputStream.readLine();
                selection[i] = line;
            }
                    
        inputStream.close();

        print("");

        return selection;
        }
    
    // Contains the 'play' section
    //
    public static void play () throws IOException
        {
        QuestionData[] questionArray = createQuestionData(qSetAsk());
        String user_name;
        String end_balance;
        int performance_points = 0; // Initialise
        int performance_percentage = 0; // Initialise
        String performance;

        user_name = gameRules(getNumQuestions(questionArray));

        end_balance = moneyDoubleToString(questionAsk(questionArray));

        performance_points = performanceMarkCalc(questionArray);

        performance_percentage = performancePercentageCalc(questionArray,performance_points);

        performance = performanceFind(performance_percentage);

        gameEnd(questionArray,performance_percentage, performance_points, user_name, end_balance, performance);
    
        return;
        }

    // Method that asks user to write in questions and answers
    // Maximum of 10 questions is assumed
    public static String[] created_questionFill ()
        {
        String[] inputArray;
        int inputNum;
        final int NUM_QUESTIONS;

        inputNum = Integer.parseInt(inputString("$ How many QUESTIONS? (up to 10)"));
        NUM_QUESTIONS = inputNum;

        while (inputNum <= 0 | inputNum > 10)
            {
                inputNum = Integer.parseInt(inputString("Please type in a value from 1 to 10."));
            }

        inputArray = new String[ NUM_QUESTIONS*2 ];

        for (int i=0;i<NUM_QUESTIONS;i++)
            {
                inputArray[i] = inputString("$ Type in QUESTION " + (i + 1) );
                inputArray[ i + NUM_QUESTIONS ] = inputString("$ Type in ANSWER " + (i + 1) + " (in CAPITALS)");
            }

        return inputArray;
        }
        
    // Writes the user's created questions and answers into a file
    // Stores the file in the game directory
    public static void created_questionStore (String[] inputArray) throws IOException
        {
        String gameName = inputString("$ What would you like to name your GAME");
        int count = 0;
        String[] qNameArray;

        while (gameName.equals(""))
            {
                gameName = inputString("$ Please give your GAME a name.");
            }
        
        PrintWriter write_qData = new PrintWriter (new FileWriter(gameName + ".txt"));
        
        BufferedReader read_qName = new BufferedReader (new FileReader("question_sets.txt"));

        String line = read_qName.readLine();
        while (line != null)
            {
                count++;
                line = read_qName.readLine();
            }
        
        read_qName = new BufferedReader (new FileReader("question_sets.txt"));

        qNameArray = new String[count];


        for (int i=0;i<count;i++)
            {
                qNameArray[i] = read_qName.readLine(); // merge, stuck tho
            }

        read_qName.close();
        
        PrintWriter write_qName = new PrintWriter (new FileWriter("question_sets.txt"));

        for (int i=0;i<count;i++)
            {
                write_qName.println(qNameArray[i]);  //
            }
        write_qName.println(gameName);
        
        write_qName.close();

        for (int i=0;i<inputArray.length;i++)
            {
                write_qData.println(inputArray[i]);
            }

        write_qData.close();

        print("");
        print("$ Your GAME has been saved.");
        print("$ Rerun the program to start your GAME.");
        print("");
        print("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        
        return;
        }
        
    // Contains the 'create' section
    //
    public static void create() throws IOException
        {
        String[] inputArray = created_questionFill();

        created_questionStore(inputArray);

        return;
        }

    // Contains all methods
    // Excluding the main
    public static void theGame() throws IOException
        {
        String option = askCreateOrPlay();

        if (option.equals("CREATE"))
            {
                create();
            }
        else if (option.equals("PLAY"))
            {
                play();
            }
        
        return;
        }
      
}// END class quiz_game