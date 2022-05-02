package main;

public class cube {
	double sideLength;
	
	/**
	 * 
	 * @param args
	 * 
	 * constructor that sets the side length of the cube object
	 */
	public cube(double sideLength) {
		this.sideLength = sideLength;
	}
	/**
	 * 
	 * @return
	 * 
	 * calculates the surface area of the cube object by multiplying
	 * 6 to the side length squared
	 */
	public double surfaceArea() {
		return 6 * Math.pow(sideLength, 2);
	}
	/**
	 * 
	 * @return
	 * 
	 * calculates the volume of the cube object by
	 * doing side length cubed
	 */
	public double volume() {
		return Math.pow(sideLength, 3);
	}
	
}
