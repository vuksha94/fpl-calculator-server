package com.springboot.fplcalculatorserver.models.fpl;

public class FplPick {
	private long element;
	private boolean is_captain;
	private boolean is_vice_captain;
	private int multiplier;
	private int position;
	
	public long getElement() {
		return element;
	}
	public void setElement(long element) {
		this.element = element;
	}
	public boolean isIs_captain() {
		return is_captain;
	}
	public void setIs_captain(boolean is_captain) {
		this.is_captain = is_captain;
	}
	public boolean isIs_vice_captain() {
		return is_vice_captain;
	}
	public void setIs_vice_captain(boolean is_vice_captain) {
		this.is_vice_captain = is_vice_captain;
	}
	public int getMultiplier() {
		return multiplier;
	}
	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
}
