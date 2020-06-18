package ua.com.foxminded.formula1.utils;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

class BestTimeCalculatorTest {
   private static final int NANOS_PER_SECOND = 1000000;
   
   @Test
   void calcBestTimeShouldReturnDurationIfInputStartAndEndTime() {
      LocalDateTime startDateTime = LocalDateTime.of(2020, Month.MARCH, 14, 15, 31, 29, 654 * NANOS_PER_SECOND);
      
      LocalDateTime endDateTime = LocalDateTime.of(2020, Month.MARCH, 14, 15, 31, 58, 123 * NANOS_PER_SECOND);
      
      Duration bestTime = BestTimeCalculator.calcBestTime(startDateTime, endDateTime);
      
      assertThat(bestTime.toString(), equalTo("PT28.469S"));
   }
   
   @Test
   void calcBestTimeShouldReturnDurationZeroIfInputStartOrEndTimeIsNull() {
      LocalDateTime endDateTime = LocalDateTime.of(2020, Month.MARCH, 14, 15, 31, 58, 123 * NANOS_PER_SECOND);
      
      Duration bestTime = BestTimeCalculator.calcBestTime(null, endDateTime);
      
      assertThat(bestTime, equalTo(Duration.ZERO));
   }
}
