package com.springboot.fplcalculatorserver.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.springboot.fplcalculatorserver.models.fpl.FplLeagueDetails;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class LeagueDetails {
  
  @Id
  @GeneratedValue/*(strategy = GenerationType.IDENTITY)*/
  private long id;
  
  @Column(name = "fpl_league_id", nullable = false, unique = true)
  private long fplLeagueId;
  
  @Column(name = "league_type", nullable = false)
  private String leagueType;
  
  @Column(name = "name", nullable = false)
  private String name;
  
  @JsonBackReference
  @ManyToMany(mappedBy = "managerLeagues", fetch = FetchType.LAZY)
  private List<User> leagueManagers;
  
  public LeagueDetails(FplLeagueDetails fplLeagueDetails) {
    fplLeagueId = fplLeagueDetails.getId();
    leagueType = fplLeagueDetails.getLeague_type();
    name = fplLeagueDetails.getName();
  }

  @Override
  public String toString() {
    return "LeagueDetails [id=" + id + ", fplLeagueId=" + fplLeagueId + ", leagueType=" + leagueType
        + ", name=" + name + "]";
  }
  
}
