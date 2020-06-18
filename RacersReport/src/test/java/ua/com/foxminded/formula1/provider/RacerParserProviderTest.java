package ua.com.foxminded.formula1.provider;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.CoreMatchers.not;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Test;

import ua.com.foxminded.formula1.domain.Racer;
import ua.com.foxminded.formula1.utils.BestTimeCalculator;

class RacerParserProviderTest {
   private final ParserProvider<Racer> parser = new RacerParserProvider();
   
   private static final int NANOS_PER_SECOND = 1000000;

   @Test
   void parseShouldReturnSortedRacersList() {
      List<Racer> racers = parser.parse(Arrays.asList("NHR_Nico Hulkenberg_RENAULT", "SVF_Sebastian Vettel_FERRARI", "PGS_Pierre Gasly_SCUDERIA TORO ROSSO HONDA", "SSW_Sergey Sirotkin_WILLIAMS MERCEDES", "EOF_Esteban Ocon_FORCE INDIA MERCEDES"), 
            Arrays.asList("SVF2018-05-24_12:02:58.917", "NHR2018-05-24_12:02:49.914", "TTT2018-05-24_12:07:23.645", "SSW2018-05-24_12:16:11.648", "EOF2018-05-24_12:17:58.810"),
            Arrays.asList("TTT2018-05-24_12:04:03.332", "NHR2018-05-24_12:04:02.979", "PGS2018-05-24_12:08:36.586", "SSW2018-05-24_12:17:24.354", "EOF2018-05-24_12:19:11.838"));
      
      assertThat(racers, not(IsEmptyCollection.empty()));
      
      assertThat(racers, hasSize(5));
      
      assertThat(racers, contains( Racer.builder().withName("Sebastian Vettel").withTeam("FERRARI").withDuration(Duration.ZERO).build(),
            Racer.builder().withName("Pierre Gasly").withTeam("SCUDERIA TORO ROSSO HONDA").withDuration(Duration.ZERO).build(),
            Racer.builder().withName("Sergey Sirotkin").withTeam("WILLIAMS MERCEDES")
               .withDuration(BestTimeCalculator.calcBestTime(
                     LocalDateTime.of(2018, Month.MAY, 24, 12, 16, 11,  648 * NANOS_PER_SECOND), 
                     LocalDateTime.of(2018, Month.MAY, 24, 12, 17, 24,  354 * NANOS_PER_SECOND))).build(),
            Racer.builder().withName("Esteban Ocon").withTeam("FORCE INDIA MERCEDES")
               .withDuration(BestTimeCalculator.calcBestTime(
                     LocalDateTime.of(2018, Month.MAY, 24, 12, 17, 58,  810 * NANOS_PER_SECOND), 
                     LocalDateTime.of(2018, Month.MAY, 24, 12, 19, 11,  838 * NANOS_PER_SECOND))).build(),
            Racer.builder().withName("Nico Hulkenberg").withTeam("RENAULT")
               .withDuration(BestTimeCalculator.calcBestTime(
                     LocalDateTime.of(2018, Month.MAY, 24, 12, 02, 49,  914 * NANOS_PER_SECOND), 
                     LocalDateTime.of(2018, Month.MAY, 24, 12, 04, 02,  979 * NANOS_PER_SECOND))).build()));
   }
}
