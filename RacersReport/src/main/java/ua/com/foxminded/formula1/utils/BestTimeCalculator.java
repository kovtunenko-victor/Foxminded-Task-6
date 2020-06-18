package ua.com.foxminded.formula1.utils;

import java.time.Duration;
import java.time.LocalDateTime;

public final class BestTimeCalculator { 
   private BestTimeCalculator() { }
   
   public static Duration calcBestTime(LocalDateTime startDateTime, LocalDateTime endDateTime) {
      return (startDateTime == null || endDateTime == null) ? 
            Duration.ZERO : 
            Duration.between(startDateTime, endDateTime); 
   }
}
