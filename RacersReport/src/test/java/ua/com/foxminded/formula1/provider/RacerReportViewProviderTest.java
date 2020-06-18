package ua.com.foxminded.formula1.provider;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ua.com.foxminded.formula1.domain.Racer;
import ua.com.foxminded.formula1.utils.BestTimeCalculator;

class RacerReportViewProviderTest {
   private static final int NANOS_PER_SECOND = 1000000;
   private final RacerReportViewProvider reportViewer = new RacerReportViewProvider();

   @Test
   void parseShouldReturnSortedRacersList() {
      List<Racer> racers = new ArrayList<>();

      for (int i = 1; i <= 20; i++) {
         LocalDateTime baseTime = LocalDateTime.of(2020, Month.MARCH, 14, 15, 31, 48,  123 * NANOS_PER_SECOND);
 
         Racer racer = Racer.builder()
               .withName("Test Test " + i)
               .withTeam("Test Team " + i)
               .withDuration(BestTimeCalculator.calcBestTime(baseTime, baseTime.plusSeconds(i).plusNanos(i*NANOS_PER_SECOND)))
               .build();
         
         racers.add(racer);
      }

      String result = reportViewer.provideView(racers);

      assertThat(result, equalTo("   1 Test Test 1         |Test Team 1                   |00:01.001\r\n" + 
                                 "   2 Test Test 2         |Test Team 2                   |00:02.002\r\n" + 
                                 "   3 Test Test 3         |Test Team 3                   |00:03.003\r\n" + 
                                 "   4 Test Test 4         |Test Team 4                   |00:04.004\r\n" + 
                                 "   5 Test Test 5         |Test Team 5                   |00:05.005\r\n" + 
                                 "   6 Test Test 6         |Test Team 6                   |00:06.006\r\n" + 
                                 "   7 Test Test 7         |Test Team 7                   |00:07.007\r\n" + 
                                 "   8 Test Test 8         |Test Team 8                   |00:08.008\r\n" + 
                                 "   9 Test Test 9         |Test Team 9                   |00:09.009\r\n" + 
                                 "  10 Test Test 10        |Test Team 10                  |00:10.010\r\n" + 
                                 "  11 Test Test 11        |Test Team 11                  |00:11.011\r\n" + 
                                 "  12 Test Test 12        |Test Team 12                  |00:12.012\r\n" + 
                                 "  13 Test Test 13        |Test Team 13                  |00:13.013\r\n" + 
                                 "  14 Test Test 14        |Test Team 14                  |00:14.014\r\n" + 
                                 "  15 Test Test 15        |Test Team 15                  |00:15.015\r\n" + 
                                 "-----------------------------------------------------------------\r\n" + 
                                 "  16 Test Test 16        |Test Team 16                  |00:16.016\r\n" + 
                                 "  17 Test Test 17        |Test Team 17                  |00:17.017\r\n" + 
                                 "  18 Test Test 18        |Test Team 18                  |00:18.018\r\n" + 
                                 "  19 Test Test 19        |Test Team 19                  |00:19.019\r\n" + 
                                 "  20 Test Test 20        |Test Team 20                  |00:20.020\r\n"));
   }
   
   @Test
   void parseShouldReturnLessFifteenSortedRacersList() {
      List<Racer> racers = new ArrayList<>();

      for (int i = 1; i <= 14; i++) {
         LocalDateTime baseTime = LocalDateTime.of(2020, Month.MARCH, 14, 15, 31, 48,  123 * NANOS_PER_SECOND);
 
         Racer racer = Racer.builder()
               .withName("Test Test " + i)
               .withTeam("Test Team " + i)
               .withDuration(BestTimeCalculator.calcBestTime(baseTime, baseTime.plusSeconds(i).plusNanos(i*NANOS_PER_SECOND)))
               .build();
         
         racers.add(racer);
      }

      String result = reportViewer.provideView(racers);

      assertThat(result, equalTo("   1 Test Test 1         |Test Team 1                   |00:01.001\r\n" + 
                                 "   2 Test Test 2         |Test Team 2                   |00:02.002\r\n" + 
                                 "   3 Test Test 3         |Test Team 3                   |00:03.003\r\n" + 
                                 "   4 Test Test 4         |Test Team 4                   |00:04.004\r\n" + 
                                 "   5 Test Test 5         |Test Team 5                   |00:05.005\r\n" + 
                                 "   6 Test Test 6         |Test Team 6                   |00:06.006\r\n" + 
                                 "   7 Test Test 7         |Test Team 7                   |00:07.007\r\n" + 
                                 "   8 Test Test 8         |Test Team 8                   |00:08.008\r\n" + 
                                 "   9 Test Test 9         |Test Team 9                   |00:09.009\r\n" + 
                                 "  10 Test Test 10        |Test Team 10                  |00:10.010\r\n" + 
                                 "  11 Test Test 11        |Test Team 11                  |00:11.011\r\n" + 
                                 "  12 Test Test 12        |Test Team 12                  |00:12.012\r\n" + 
                                 "  13 Test Test 13        |Test Team 13                  |00:13.013\r\n" + 
                                 "  14 Test Test 14        |Test Team 14                  |00:14.014\r\n"));
   }
   
   @Test
   void parseShouldEmptyResultIfInputListIsEmpty() {
      List<Racer> racers = new ArrayList<>();

      String result = reportViewer.provideView(racers);

      assertThat(result, equalTo(""));
   }
}
