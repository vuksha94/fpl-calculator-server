package com.springboot.fplcalculatorserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.springboot.fplcalculatorserver.config.AppConfig;
import com.springboot.fplcalculatorserver.exceptions.ErrorCode;
import com.springboot.fplcalculatorserver.exceptions.MyEntityNotFoundException;
import com.springboot.fplcalculatorserver.models.fpl.FplBootstrapStatic;
import com.springboot.fplcalculatorserver.models.fpl.FplLeagueStandings;
import com.springboot.fplcalculatorserver.models.fpl.FplManagerDetails;
import com.springboot.fplcalculatorserver.models.fpl.FplManagerPicks;

@Service
public class FplApiService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public FplBootstrapStatic getBootstrapStatic() {
		return restTemplate.getForObject(AppConfig.apiUrl + 
		    "/bootstrap-static/", FplBootstrapStatic.class);
	}
	
	public FplLeagueStandings getLeagueStandings(String leagueId, String pageNumber) {
		String url = AppConfig.apiUrl + "leagues-classic/" + leagueId + 
		    "/standings/?page_standings=" + pageNumber;
		FplLeagueStandings fplLeagueStandings = null;
		try {
          fplLeagueStandings = restTemplate.getForObject(url, FplLeagueStandings.class);
        } catch (RestClientException e) {
          throw new MyEntityNotFoundException(
              ErrorCode.LEAGUE_STANDINGS_NOT_FOUND.getMsg(), ErrorCode.LEAGUE_STANDINGS_NOT_FOUND);
        }
		return fplLeagueStandings;
	}
	
	public FplManagerPicks getManagerPicks(String managerId, String currentGW) {
		String url = AppConfig.apiUrl + "/entry/" + managerId + "/event/" + currentGW + "/picks/";
		FplManagerPicks fplManagerPicks = null;
		try {
		  fplManagerPicks = restTemplate.getForObject(url, FplManagerPicks.class);
        } catch (RestClientException e) {
          throw new MyEntityNotFoundException(
              ErrorCode.MANAGER_PICKS_NOT_FOUND.getMsg(), ErrorCode.MANAGER_PICKS_NOT_FOUND);
        }
		return fplManagerPicks;
	}
	
	public FplManagerDetails getManagersDetails(long fplManagerId) {
		String url = AppConfig.apiUrl + "/entry/" + fplManagerId + "/";
		FplManagerDetails fplManagerDetails = null;
        try {
          fplManagerDetails = restTemplate.getForObject(url, FplManagerDetails.class);
        } catch (RestClientException e) {
          System.out.println(e);
          System.out.println(url);
          throw new MyEntityNotFoundException(
              ErrorCode.MANAGER_ID_NOT_FOUND.getMsg(), ErrorCode.MANAGER_ID_NOT_FOUND);
        }
        return fplManagerDetails;
	}
	
}
