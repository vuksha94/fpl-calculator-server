package com.springboot.fplcalculatorserver.accessingdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.fplcalculatorserver.entities.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
}
