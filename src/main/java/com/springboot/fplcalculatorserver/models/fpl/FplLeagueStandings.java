package com.springboot.fplcalculatorserver.models.fpl;

public class FplLeagueStandings {
	
	private FplLeagueDetails league;
	private FplStandings standings;
	
	public FplLeagueDetails getLeague() {
		return league;
	}
	public void setLeague(FplLeagueDetails league) {
		this.league = league;
	}
	public FplStandings getStandings() {
		return standings;
	}
	public void setStandings(FplStandings standings) {
		this.standings = standings;
	}
	
}
