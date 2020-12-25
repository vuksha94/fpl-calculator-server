package com.springboot.fplcalculatorserver.models.fpl;

import java.util.List;

public class FplStandings {
	
	private boolean has_next;
	private int page;
	private List<FplStandingEntry> results;
	
	public boolean isHas_next() {
		return has_next;
	}
	public void setHas_next(boolean has_next) {
		this.has_next = has_next;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	public List<FplStandingEntry> getResults() {
		return results;
	}
	public void setResults(List<FplStandingEntry> results) {
		this.results = results;
	}
	
}
