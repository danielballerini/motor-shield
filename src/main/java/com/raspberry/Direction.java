package com.raspberry;

public class Direction {

	private int directionValue;
	private int allMotorsDirection;

	public Direction(int directionValue, int allMotorsDirection) {
		super();
		this.directionValue = directionValue;
		this.allMotorsDirection = allMotorsDirection;
	}

	public int getDirectionValue() {
		return directionValue;
	}

	public void setDirectionValue(int directionValue) {
		this.directionValue = directionValue;
	}

	public int getAllMotorsDirection() {
		return allMotorsDirection;
	}

	public void setAllMotorsDirection(int allMotorsDirection) {
		this.allMotorsDirection = allMotorsDirection;
	}

}
