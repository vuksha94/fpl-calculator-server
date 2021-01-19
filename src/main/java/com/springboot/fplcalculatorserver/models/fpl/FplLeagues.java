package com.springboot.fplcalculatorserver.models.fpl;

import java.util.List;
import lombok.Data;

@Data
public class FplLeagues {
	private List<FplLeagueDetails> classic;
}
