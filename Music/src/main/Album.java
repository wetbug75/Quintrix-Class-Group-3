package main;

/**
 * 
 * @author devinlewis
 *
 *this class creates album objects
 */
public class Album {
	private String albumTitle;
	private String artist;
	private String albumGenre;
	private Song favoriteTrack;
	private int trackNumber;
	
	static int numAlbums;
	
	/**
	 * 
	 * @param title
	 * @param favoriteTrack
	 * @param trackNumber
	 * 
	 * this is an album constructor that gives an album a title 
	 * favorite track track number genre and artist 
	 * based on the parameters entered 
	 * 
	 * also increases the total number of albums in the system
	 */
	public Album(String title, Song favoriteTrack, int trackNumber) {
		this.albumTitle = title;
		this.favoriteTrack = favoriteTrack;
		this.trackNumber = trackNumber;
		
		this.artist = favoriteTrack.getArtist();
		this.albumGenre = favoriteTrack.getGenre();
		
		numAlbums++;
	}
	/**
	 * 
	 * @param title
	 * @param favoriteTrack
	 * 
	 * chained version of the original constructor that autosets the artist 
	 * and genre based on the artist and genre of the favoritetrack in the 
	 * parameter
	 * 
	 * also autosets the tracknumber of the album to 1
	 */
	public Album(String title, Song favoriteTrack) {
		this.albumTitle = title;
		this.artist = favoriteTrack.getArtist();
		this.albumGenre = favoriteTrack.getGenre();
		this.favoriteTrack = favoriteTrack;
		this.trackNumber = 1;
		
		numAlbums++;
	}
	
	public String getAlbumTitle() {
		return this.albumTitle;
	}
	
	
	public Song getfavoriteTrack() {
		return this.favoriteTrack;
	}
	
	
	public int gettrackNumber() {
		return this.trackNumber;
	}
	
	public void setAlbumGenre(String albumGenre) {
		this.albumGenre = albumGenre;
		favoriteTrack.setGenre(albumGenre);
	}
	/**
	 * 
	 * @return this method will return the title artist and genre of the 
	 * album it is called on
	 */
	public String albumtoString() {
		return this.albumTitle + ", " + this.artist + ", " + this.albumGenre;
	}


}
