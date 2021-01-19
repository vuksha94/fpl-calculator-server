package com.springboot.fplcalculatorserver.models.fpl;

import lombok.Data;

@Data
public class FplManagerDetails {
	private long id;
	private String name;
	private String player_first_name;
	private String player_last_name;
	private FplLeagues leagues;
}
