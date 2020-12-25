package com.springboot.fplcalculatorserver.models.fpl;

import java.util.List;

public class FplBootstrapStatic {
	
	private List<FplPlayer> elements;
	private List<FplEvent> events;
	
	public List<FplPlayer> getElements() {
		return elements;
	}
	public void setElements(List<FplPlayer> elements) {
		this.elements = elements;
	}
	public List<FplEvent> getEvents() {
		return events;
	}
	public void setEvents(List<FplEvent> events) {
		this.events = events;
	}

}
