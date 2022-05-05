package main;

/**
 * 
 * @author devinlewis
 * version 1 
 * 
 * this class constructs a Song with a title artist and genre
 */

public class Song {
	private String title;
	private String artist;
	private String genre;
	
	
	/**
	 * 
	 * @param title
	 * @param artist
	 * @param genre
	 * 
	 * this is the constructor for Song objects 
	 */
	public Song(String title, String artist, String genre) {
		this.title = title;
		this.artist = artist;
		this.genre = genre;
	}
	
	/**
	 * 
	 * @param title
	 * @param artist
	 * 
	 * chained instructor for a Song object where the class is preset as unknown
	 */
	public Song(String title, String artist) {
		this.title = title;
		this.artist = artist;
		this.genre = "unknown";
	}
	
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String newTitle) {
		this.title = newTitle;
	}
	
	
	
	
	
	public String getArtist() {
		return this.artist;
	}
	public void setArtist(String newArtist) {
		this.artist = newArtist;
	}
	
	
	
	
	
	public String getGenre() {
		return this.genre;
	}
	public void setGenre(String newGenre) {
		this.genre = newGenre;
	}

	
	/**
	 * @return this method returns the title artist and genre of the song object it is called on
	 */
	public String toString() {
		return this.title + ", " + this.artist +  ", " + this.genre;
	}

}
