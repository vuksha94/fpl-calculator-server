package com.springboot.fplcalculatorserver.models.fpl;

public class FplLeagueDetails {
	
	private long id;
	private String league_type;
	private String name;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLeague_type() {
		return league_type;
	}
	public void setLeague_type(String league_type) {
		this.league_type = league_type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
