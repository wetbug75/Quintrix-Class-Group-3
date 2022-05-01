package main;

import java.util.Scanner;

/**
 * 
 * @author devinlewis
 * 
 * This is the change calculator
 * just enter your amount you need
 * and we'll tell you what bills to bring
 *
 */
public class changecalculator {

	public static void main(String[] args) {
		Scanner change = new Scanner(System.in);
		
		System.out.println("How much money do you need?");
		
		double amount = change.nextDouble();
		System.out.println("Amount: " + amount);
		
		
		int amountOfTwenties = 0;
		int amountOfTens = 0;
		int amountOfFives = 0;
		int amountOfOnes = 0;
		int amountOfQuarters = 0;
		int amountOfDimes = 0;
		int amountOfNickels = 0;
		int amountOfPennies = 0;
		
		/**
		 * calculating the remainder needed after bills by using modulo
		 * 
		 * and determining how many bills by subtracting with a second variable named balance 
		 * so that amount isn't altered
		 * 
		 */
		if(amount >= 20) {
			double balance = amount;
			
			amount = amount % 20;
			
			
			while(balance >= 20) {
				balance -= 20;
				
				amountOfTwenties++;
			}	
		}
		
		if(amount >= 10) {
			double balance = amount;
			
			amount = amount % 10;
			
			
			while(balance >= 10) {
				balance -= 10;
				
				amountOfTens++;
			}	
		}
		
		if(amount >= 5) {
			double balance = amount;
			
			amount = amount % 5;
			
			while(balance >= 5) {
				balance -= 5;
				
				amountOfFives++;
			}	
		}
		
		if(amount >= 1) {
			double balance = amount;
			
			amount = amount % 1;
			
			while(balance >= 1) {
				balance -= 1;
				
				amountOfOnes++;
			}	
		}
		
		if(amount >= 0.25) {
			double balance = amount;
			
			amount = amount % 0.25;
			
			while(balance >= 0.25) {
				balance -= 0.25;
				
				amountOfQuarters++;
			}
		}
		
		if(amount >= 0.10) {
			double balance = amount;
			
			amount = amount % 0.10;
			
			while(balance >= 0.10) {
				balance -= 0.10;
				
				amountOfDimes++;
			}
		}
		
		if(amount >= 0.05) {
			double balance = amount;
			
			amount = amount % 0.05;
			
			while(balance >= 0.05) {
				balance -= 0.05;
				
				amountOfNickels++;
			}
		}
		
		if(amount >= 0.01) {
			double balance = amount;
			
			amount = amount % 0.01;
			
			while(balance >= 0.01) {
				balance -= 0.01;
				
				amountOfPennies++;
			}
		}
		
		
		
		System.out.println(amountOfTwenties + " twenty dollar bills");
		System.out.println(amountOfTens + " ten dollar bills");
		System.out.println(amountOfFives + " five dollar bills");
		System.out.println(amountOfOnes + " one dollar bills");
		System.out.println(amountOfQuarters + " quarters");
		System.out.println(amountOfDimes + " dimes");
		System.out.println(amountOfNickels + " nickels");
		System.out.println(amountOfPennies + " pennies");
		
		
	}

}
