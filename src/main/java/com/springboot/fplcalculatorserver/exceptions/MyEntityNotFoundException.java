package com.springboot.fplcalculatorserver.exceptions;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class MyEntityNotFoundException extends MyBusinessException {
  private static final long serialVersionUID = 4836960176058647499L;

  public MyEntityNotFoundException(String message, Throwable cause, ErrorCode code) {
    super(message, cause, code);
  }
  
  public MyEntityNotFoundException(String msg, ErrorCode errorCode) {
    super(msg, errorCode);
  }

  public MyEntityNotFoundException(Class clazz, ErrorCode code, String... searchParamsMap) {
    super(MyEntityNotFoundException.generateMessage(
        clazz.getSimpleName(), toMap(String.class, String.class, searchParamsMap)),
        code);
  }
  
  private static String generateMessage(String entity, Map<String, String> searchParams) {
    return entity +
        " was not found for parameters " +
        searchParams;
  }
  
  public static <K, V> Map<K, V> toMap(Class<K> keyClass,
      Class<V> valueClass, Object ...entries) {
    HashMap<K, V> map = new HashMap<>();
    int paramsLength = entries.length;
    if (paramsLength % 2 == 1) {
      throw new IllegalArgumentException("Invalid entries");
    }
    for(int i = 0; i < paramsLength / 2; i++) {
      map.put(keyClass.cast(entries[i * 2]),
          valueClass.cast(entries[i * 2 + 1]));
    }
    
    return map;
  }
  
  public static <K, V> Map<K, V> toMapStreams(
      Class<K> keyType, Class<V> valueType, Object... entries) {
    if (entries.length % 2 == 1)
      throw new IllegalArgumentException("Invalid entries");
    return IntStream.range(0, entries.length / 2).map(i -> i * 2)
          .collect(HashMap::new,
                  (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])),
                  Map::putAll); // (m1, m2) -> m1.putAll(m2)
  }
  
}
