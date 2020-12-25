package com.springboot.fplcalculatorserver.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fplcalculatorserver.models.ApiResponse;
import com.springboot.fplcalculatorserver.models.fpl.FplBootstrapStatic;
import com.springboot.fplcalculatorserver.models.fpl.FplLeagueStandings;
import com.springboot.fplcalculatorserver.models.fpl.FplManagerDetails;
import com.springboot.fplcalculatorserver.models.fpl.FplManagerPicks;
import com.springboot.fplcalculatorserver.services.FplApiService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FPLApiController {

	@Autowired
	private FplApiService fplApiService;

	@GetMapping("/bootstrap-static/")
	public ResponseEntity<ApiResponse<?>> getBootstrapStatic() {
		final FplBootstrapStatic bootstrapStatic = fplApiService.getBootstrapStatic();
		return ResponseEntity.ok(new ApiResponse<FplBootstrapStatic>().success(bootstrapStatic));
	}

	@GetMapping("/leagues-classic/{id}/{num}/")
	public ResponseEntity<ApiResponse<?>> getLeagueStandings(@PathVariable(value = "id") String leagueId,
			@PathVariable(value = "num") String pageNumber) {
		final FplLeagueStandings leagueStandings = fplApiService.getLeagueStandings(leagueId, pageNumber);
		return ResponseEntity.ok(new ApiResponse<FplLeagueStandings>().success(leagueStandings));
	}

	@GetMapping("/manager-picks/{id}/{gw}/")
	public ResponseEntity<ApiResponse<?>> getManagerPicks(@PathVariable(value = "id") String managerId,
			@PathVariable(value = "gw") String currentGW) {
		
		final FplManagerPicks managerPicks = fplApiService.getManagerPicks(managerId, currentGW);
		return ResponseEntity.ok(new ApiResponse<FplManagerPicks>().success(managerPicks));
	}
	
	@GetMapping("/manager-details/{id}/")
	public ResponseEntity<ApiResponse<?>> getManagerPicks(@PathVariable(value = "id") String managerId) {
		
		final FplManagerDetails managerDetails = fplApiService.getManagersDetails(Long.parseLong(managerId));
		return ResponseEntity.ok(new ApiResponse<FplManagerDetails>().success(managerDetails));
	}

}
