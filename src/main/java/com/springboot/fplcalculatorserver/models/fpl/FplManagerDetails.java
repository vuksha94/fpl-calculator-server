package com.springboot.fplcalculatorserver.models.fpl;

public class FplManagerDetails {
	
	private long id;
	private String name;
	private String player_first_name;
	private String player_last_name;
	private FplLeagues leagues;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlayer_first_name() {
		return player_first_name;
	}
	public void setPlayer_first_name(String player_first_name) {
		this.player_first_name = player_first_name;
	}
	public String getPlayer_last_name() {
		return player_last_name;
	}
	public void setPlayer_last_name(String player_last_name) {
		this.player_last_name = player_last_name;
	}
	public FplLeagues getLeagues() {
		return leagues;
	}
	public void setLeagues(FplLeagues leagues) {
		this.leagues = leagues;
	}
	
}
