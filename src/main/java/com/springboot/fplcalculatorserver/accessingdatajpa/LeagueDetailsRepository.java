package com.springboot.fplcalculatorserver.accessingdatajpa;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.fplcalculatorserver.entities.LeagueDetails;


public interface LeagueDetailsRepository extends JpaRepository<LeagueDetails, Long> {
  public Optional<LeagueDetails> findByFplLeagueId(long id);
}
