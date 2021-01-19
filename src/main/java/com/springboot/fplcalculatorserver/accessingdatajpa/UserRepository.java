package com.springboot.fplcalculatorserver.accessingdatajpa;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.fplcalculatorserver.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
  //@Cacheable(value = "emails")
  Optional<User> findByEmail(String email);
}
