package com.springboot.fplcalculatorserver.accessingdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.fplcalculatorserver.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
