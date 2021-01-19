package com.springboot.fplcalculatorserver.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Map;
import org.junit.jupiter.api.Test;

class MyEntityNotFoundExceptionTest {

  @Test
  void testToMapWithMapSize1() {
    final Map<String, String> map = MyEntityNotFoundException.toMap(
        String.class, String.class, "id", "12");
    assertNotNull(map, "Map is null");
    assertEquals(1, map.size(), "Map size should be 1");
    assertEquals("12", map.get("id"));
  }

  @Test
  void testToMapWithMapSizeMultiple() {
    final Map<String, String> map = MyEntityNotFoundException.toMap(
        String.class, String.class, "id", "122", "name", "Stefan","last name", "Vukasinovic");
    assertNotNull(map, "Map is null");
    assertEquals(3, map.size(), "Map size should be 1");
    assertEquals("122", map.get("id"));
    assertEquals("Stefan", map.get("name"));
    assertEquals("Vukasinovic", map.get("last name"));
  }
  
  @Test
  void testToMapWithIllegalNumberOfArguments() {
    assertThrows(IllegalArgumentException.class, () ->
        MyEntityNotFoundException.toMap(String.class, String.class, "id", "122", "name"));
  }
  
  @Test
  void testToMapStream() {
    final Map<String, String> map = MyEntityNotFoundException.toMapStreams(
        String.class, String.class, "id", "122", "name", "Stefan","last name", "Vukasinovic");
    assertNotNull(map, "Map is null");
    assertEquals(3, map.size(), "Map size should be 1");
    assertEquals("122", map.get("id"));
    assertEquals("Stefan", map.get("name"));
    assertEquals("Vukasinovic", map.get("last name"));
  }
}
