/*
 * Class: CMSC203 
 * Instructor: Prof. Monshi
 * Description: This program generates a ramdom number and asks the user to find it.
 * Due: 2/23/2021
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: Kevin Eguida
*/

import java.util.Scanner;
public class RandomNumberGuesser 
{

	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in); //Allows the user to enter inputs.
		int randNum; // holds the random number
		int nextGuess; // holds the guess of the user
		int highGuess; // Starts at 100 and remembers the highest number guessed
		int lowGuess; // Starts at 0 and remembers the lowest number guessed
		String encore; // Allows the user to try again the program


		do 
		{
			RNG.resetCount(); //(Re)Sets the counter to 0
			randNum = RNG.rand(); // Assigns the variable to an random number.
			highGuess = 100;
			lowGuess = 0;
			System.out.println("Enter your first guess: ");
			
			do
			{
				nextGuess = input.nextInt(); // Receive the user's guess
		
		
				if(RNG.inputValidation(nextGuess, lowGuess, highGuess)) //If the guess is not between the lower and highest guess then it notices the user the range in which the guess should be
				{
		
					if(nextGuess == randNum)
					{
						System.out.println("Number of guesses is " + RNG.getCount());
					} 
					
					else if(nextGuess < randNum) 
					{
						System.out.println("Number of guesses is " + RNG.getCount());
						System.out.println("your guess was too low");
						lowGuess = nextGuess;
						System.out.println("Enter your next guess between " + lowGuess + " and " + highGuess + "\n");
					}
					
					else if(nextGuess > randNum) 
					{
						System.out.println("Number of guesses is " + RNG.getCount());
						System.out.println("your guess was too high");
						highGuess = nextGuess;
						System.out.println("Enter your next guess between " + lowGuess + " and " + highGuess + "\n");
					}
		
				}
				
			}while(nextGuess != randNum); // If the user guessed correctly then the loop ends
	
			System.out.println("Congragulations!! you guessed correctly");
			System.out.println("Try again?(yes or no)");
			encore = input.next();
			System.out.println("\n");
				
		}while(encore.equalsIgnoreCase("yes")||encore.equalsIgnoreCase("y"));
		
		
		ThanksVisitor();
	}
		
	public static void ThanksVisitor()
	{
		final String PROGRAMMER = "Kevin Eguida";
		final String PROJECT = "Assignment 2";
		final String DUEDATE = "02/22/2022";
		
		System.out.println("Thank you for testing my program!");
		System.out.println("Programmer: " + PROGRAMMER);
		System.out.println("CMSC203: " + PROJECT);
		System.out.println("Due date: " + DUEDATE);
	}
}
