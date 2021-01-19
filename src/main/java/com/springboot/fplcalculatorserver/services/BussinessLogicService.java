package com.springboot.fplcalculatorserver.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.fplcalculatorserver.accessingdatajpa.LeagueDetailsRepository;
import com.springboot.fplcalculatorserver.entities.LeagueDetails;
import com.springboot.fplcalculatorserver.models.fpl.FplLeagueDetails;

@Service
public class BussinessLogicService {
  
  @Autowired
  private LeagueDetailsRepository leagueDetailsRepository;
  
  public List<LeagueDetails> getLeagueDetails(List<FplLeagueDetails> managerLeagues) {
    final List<LeagueDetails> newLeaguesToAdd = 
        managerLeagues.stream()
              .filter(league -> leagueDetailsRepository.findByFplLeagueId(league.getId()).isEmpty())
              .map(LeagueDetails::new)
              .collect(Collectors.toList());
    leagueDetailsRepository.saveAll(newLeaguesToAdd);
    
    final List<LeagueDetails> result = new ArrayList<>();
    
    managerLeagues.stream()
                  .forEach(league -> {
                    result.add(leagueDetailsRepository.findByFplLeagueId(league.getId()).get());
                  });
    
    return result;
  }
}
