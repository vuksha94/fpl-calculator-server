package com.springboot.fplcalculatorserver.models.fpl;

public class FplPlayer {
	
	private long id;
	private String web_name;
	private String first_name;
	private String second_name;
	private int goals_scored;
	private int assists;
	private int minutes;
	
	public String getWeb_name() {
		return web_name;
	}
	public void setWeb_name(String web_name) {
		this.web_name = web_name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getSecond_name() {
		return second_name;
	}
	public void setSecond_name(String second_name) {
		this.second_name = second_name;
	}
	public int getGoals_scored() {
		return goals_scored;
	}
	public void setGoals_scored(int goals_scored) {
		this.goals_scored = goals_scored;
	}
	public int getAssists() {
		return assists;
	}
	public void setAssists(int assists) {
		this.assists = assists;
	}
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	
}
