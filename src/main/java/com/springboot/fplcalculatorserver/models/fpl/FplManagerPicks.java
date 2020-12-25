package com.springboot.fplcalculatorserver.models.fpl;

import java.util.List;

public class FplManagerPicks {
	private List<FplPick> picks;

	public List<FplPick> getPicks() {
		return picks;
	}

	public void setPicks(List<FplPick> picks) {
		this.picks = picks;
	}
	
}
