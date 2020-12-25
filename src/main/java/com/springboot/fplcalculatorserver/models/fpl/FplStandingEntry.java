package com.springboot.fplcalculatorserver.models.fpl;

/**
 * Represents an entry in fpl standings
 * which is a manager's data with points etc...
 * 
 * @author stefan.vukasinovic
 *
 */
public class FplStandingEntry {
	private long entry;

	public long getEntry() {
		return entry;
	}

	public void setEntry(long entry) {
		this.entry = entry;
	}
	
}
