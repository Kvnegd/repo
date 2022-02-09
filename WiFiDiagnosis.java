/*
 * Class: CMSC203 
 * Instructor: Khandan Monshi
 * Description: This program is the trouble shooting interface of a WiFi router. 
 				Gives the user instructions to follow and ask if they worked.
 * Due: 2/8/2022
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: Kevin Eguida
*/

import java.util.Scanner;
public class WiFiDiagnosis 
{
	public static void main(String[] args)
	{
		
		Scanner input = new Scanner (System.in); //Creates an scanner that will be used to get the user's answer.
		String response; //This variable will hold the user's answer.
		
		System.out.println("If you have a problem with internet connectivity, this WiFi Diagnosis might work.\n");
		

		System.out.print("First step: reboot your computer are you able to connect with the internet? (yes or no)\n");
		response = input.nextLine(); // Allows the user to enter an answer and then stores it in the variable.
		response = response.toLowerCase();

		if (response.equals("no")||response.equals("n")) // If the user enters no or n, they will be ask to follow step 2
		{

			System.out.print("Second step: reboot your router.\n"
					+ "Now are you able to connect with the internet? (yes or no)\n");
			response = input.nextLine();
			response = response.toLowerCase();

			if(response.equals("no")||response.equals("n")) //step 2 did not work. The user will be asked to follow step 3
			{

				System.out.print("Third step: make sure the cables to your router are plugged in firmly and your router is getting power.\n"
						+ "Now are you able to connect with the internet? (yes or no)\n");
				response = input.nextLine();
				response = response.toLowerCase();

				if(response.equals("no")||response.equals("n")) //step 3 did not work. The user will be asked to follow step 4
				{

					System.out.print("Fourth step: move your computer closer to your router.\n"
							+ "Now are you able to connect with the internet? (yes or no)\n");
					response = input.nextLine();
					response = response.toLowerCase();

					if(response.equals("no")||response.equals("n")) // None of the steps worked
					{
						System.out.print("Fifth step: contact your ISP.\n"
								+ "Make sure your ISP is hooked up to your router.");
					}
					else if (response.equals("yes")||response.equals("y")) // The user entered yes or y. Step 4 worked
					{
						System.out.println("Moving your computer closer to the router seemed to work.");	
					}
					
				} 
				else if (response.equals("yes")||response.equals("y")) // Step 3 worked
				{
					System.out.println("Checking the router's cables seemed to work.");	
				}
				
			} 
			else if(response.equals("yes")||response.equals("y")) // Step2 worked
			{
				System.out.println("Rebooting your router seemed to work.\n");
			}
			
		}
		else if(response.equals("yes")||response.equals("y"))  // Step1 worked
		{	
		System.out.println("Rebooting your computer seemed to work.\n");	
		}
		
	}

}

