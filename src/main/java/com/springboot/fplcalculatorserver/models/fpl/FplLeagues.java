package com.springboot.fplcalculatorserver.models.fpl;

import java.util.List;

public class FplLeagues {
	private List<FplLeagueDetails> classic;

	public List<FplLeagueDetails> getClassic() {
		return classic;
	}

	public void setClassic(List<FplLeagueDetails> classic) {
		this.classic = classic;
	}
	
}
