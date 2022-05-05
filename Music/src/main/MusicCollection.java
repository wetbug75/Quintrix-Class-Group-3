package main;

import java.util.Scanner;
import java.util.ArrayList;
/**
 * 
 * @author devinlewis
 * 
 * this class allows you to view and interact with 
 * instances of the album and song classes 
 *
 */
public class MusicCollection {
	static Scanner music = new Scanner(System.in);
	/**
	 * 
	 * @param variableName
	 * 
	 * displays the options: 
	 * get favorite track which prints favorite track of the parameter
	 * change genre which changes the genre of the parameter
	 * and return which resets back to the beginning of main()
	 */
	public static void albumOptions(Album variableName) {
		System.out.println(variableName.albumtoString());
		
		System.out.println("Options: ");
		System.out.println("1. Get Favorite Track");
		System.out.println("2. Change Genre");
		System.out.println("3. Return");
		
		int userOption = music.nextInt();
		while(userOption != 3) {
			
			
			if(userOption == 1) {
				System.out.println(variableName.getfavoriteTrack());
			} 
			if(userOption == 2) {
				System.out.println("what is the new genre?");
				music.nextLine();
				String userGenre = music.nextLine();
				variableName.setAlbumGenre(userGenre);
				System.out.println(variableName.albumtoString());
			}
			System.out.println("Options: ");
			System.out.println("1. Get Favorite Track");
			System.out.println("2. Change Genre");
			System.out.println("3. Return");
	
			userOption = music.nextInt();
		}
	}
	
	public static void main(String[] args) {
		
		Song chronicles = new Song("Chronicles", "Cordae", "hip hop");
		Song over = new Song("Over", "Lucky Daye", "r&b");
		Song wtw = new Song("What They Want", "Russ");
		
		
		Album Fabev = new Album("From a Birds Eye View", chronicles, 10);
		Album candyDrip = new Album("Candydrip", over);
		Album traw = new Album("There's Really A Wolf", wtw, 11);
		
		
		ArrayList<Album> albums = new ArrayList<Album>();
		albums.add(Fabev);
		albums.add(candyDrip);
		albums.add(traw);
		
		
	/**
	 * variable used to assign integers to the albums in arraylist album 
	 */
		int order = 0;
		
		/**
		 * this for loop iterates through the album arraylist to list all the albums 
		 * in a numbered list 
		 */
		for(int i = 0; i < albums.size(); i++) {
			
			order++;
			System.out.println("#" + order + ". " + albums.get(i).getAlbumTitle());
			
		}
		/**
		 * the final touch of the main method calls the albumoptions method 
		 * on whichever album from arraylist was selected by the user in 
		 * userAlbumChoice
		 * 
		 * after albumOptions has finished its called function the program returns here for 
		 * another selection until ultimately option 0 is selected to terminate the program
		 */
		System.out.println("Choose an album (0 to cancel): ");
		int userAlbumChoice = music.nextInt();
		System.out.println(userAlbumChoice);
		
		if(userAlbumChoice == 1) {
			MusicCollection.albumOptions(Fabev);
			main(null);
		}
		if(userAlbumChoice == 2) {
			MusicCollection.albumOptions(candyDrip);
			main(null);
		}
		if(userAlbumChoice == 3) {
			MusicCollection.albumOptions(traw);
			main(null);
		}
		
		
		

	}

}
