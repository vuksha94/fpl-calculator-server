package com.springboot.fplcalculatorserver.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class UserDetails {
  @Id
  @GeneratedValue
  private long id;
  
  @JsonBackReference
  @OneToOne(mappedBy = "details", cascade = CascadeType.ALL)
  private User user;
}
