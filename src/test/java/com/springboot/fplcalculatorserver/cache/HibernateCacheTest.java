package com.springboot.fplcalculatorserver.cache;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import com.springboot.fplcalculatorserver.accessingdatajpa.UserRepository;
import com.springboot.fplcalculatorserver.entities.User;

@SpringBootTest
class HibernateCacheTest {
  
  @Autowired
  private CacheManager cacheManager;
  
  @Autowired
  EntityManager entityManger;
  
  @Autowired
  private UserRepository userRepository;

  @BeforeEach
  void setUp() throws Exception {
    userRepository.findByEmail("xxasdx5.yyy@gmail.com");
  }

  @Test
  void testSpringMethodCaching() {
     final Optional<User> user = java.util.Optional.ofNullable(cacheManager.getCache("emails"))
                       .map(c -> c.get("xxasdx5.yyy@gmail.com", User.class));
      assertTrue(user.isPresent());
  }
  
  @Test
  @Transactional
  void testSecondLevelCaching() {
    Session session = entityManger.unwrap(Session.class);
    final Optional<User> user = userRepository.findById(1L);
    session.evict(user.get()); // removing from level 1 cache
    userRepository.findById(1L);
  }

}
