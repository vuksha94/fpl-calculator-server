package com.springboot.fplcalculatorserver.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

@Data
@Entity
public class Project {
  @Id
  @GeneratedValue
  private int id;
  
  @Column
  private String title;
  
  @JsonBackReference
  @ManyToMany(mappedBy = "projects")
  private List<User> users;
}
