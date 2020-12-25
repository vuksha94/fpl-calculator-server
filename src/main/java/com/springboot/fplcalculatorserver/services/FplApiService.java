package com.springboot.fplcalculatorserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springboot.fplcalculatorserver.config.AppConfig;
import com.springboot.fplcalculatorserver.models.fpl.FplBootstrapStatic;
import com.springboot.fplcalculatorserver.models.fpl.FplLeagueStandings;
import com.springboot.fplcalculatorserver.models.fpl.FplManagerDetails;
import com.springboot.fplcalculatorserver.models.fpl.FplManagerPicks;

@Service
public class FplApiService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public FplBootstrapStatic getBootstrapStatic() {
		return restTemplate.getForObject(AppConfig.apiUrl + "/bootstrap-static/", FplBootstrapStatic.class);
	}
	
	public FplLeagueStandings getLeagueStandings(String leagueId, String pageNumber) {
		String url = AppConfig.apiUrl + "leagues-classic/" + leagueId + "/standings/?page_standings=" + pageNumber;
		return restTemplate.getForObject(url, FplLeagueStandings.class);
	}
	
	public FplManagerPicks getManagerPicks(String managerId, String currentGW) {
		String url = AppConfig.apiUrl + "/entry/" + managerId + "/event/" + currentGW + "/picks/";
		return restTemplate.getForObject(url, FplManagerPicks.class);
	}
	
	public FplManagerDetails getManagersDetails(long fplManagerId) {
		String url = AppConfig.apiUrl + "/entry/" + fplManagerId + "/";
		return restTemplate.getForObject(url, FplManagerDetails.class);
	}
	
}
