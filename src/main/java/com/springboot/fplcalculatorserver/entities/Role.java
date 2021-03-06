package com.springboot.fplcalculatorserver.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Role {

  @Id
  @GeneratedValue
  private long id;
	
  @Column(nullable = false)
  private String role;
	
  @JsonBackReference /* @JsonIgnore */
  @ManyToMany(mappedBy = "roles",cascade = CascadeType.ALL)
  private List<User> users;

  @Override
  public String toString() {
    return "Role [id=" + id + ", role=" + role + "]";
  }
	
}
