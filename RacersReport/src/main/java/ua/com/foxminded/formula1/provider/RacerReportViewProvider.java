package ua.com.foxminded.formula1.provider;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import ua.com.foxminded.formula1.domain.Racer;

public class RacerReportViewProvider implements ReportViewProvider<Racer> {
   private static final String VERTICAL_LINE = "|";

   private static final String SPACE = " ";

   private static final String HORIZONTAL_LINE = "-";

   private static final String GO_TO_NEW_LINE = "\r\n";

   private static final String ROW_INDEX_FORMAT = "%4s";

   private static final String ROW_NAME_FORMAT = "%-20s";

   private static final String ROW_TEAM_FORMAT = "%-30s";

   private static final int QUALIFYING_TRAIT_POSITION = 14;

   private static final int QUALIFYING_TRAIT_LENGTH = 65;
   
   private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("mm:ss.SSS");
   
   private static final String PLACEHOLDER = "\0";
   
   @Override
   public String provideView(List<Racer> racers) {
      StringBuilder resultString = new StringBuilder();

      for (int i = 0; i < racers.size(); i++) {
         resultString.append(String.format(ROW_INDEX_FORMAT, i + 1)).append(SPACE)
               .append(String.format(ROW_NAME_FORMAT, racers.get(i).getName())).append(VERTICAL_LINE)
               .append(String.format(ROW_TEAM_FORMAT, racers.get(i).getTeam())).append(VERTICAL_LINE)
               .append(getBestTimeStr(racers.get(i).getDuration())).append(GO_TO_NEW_LINE);

         if (i == QUALIFYING_TRAIT_POSITION) {
            resultString.append(getDemarcationLine()).append(GO_TO_NEW_LINE);
         }
      }

      return resultString.toString();
   }
   
   private String getDemarcationLine() {
      return new String(new char[QUALIFYING_TRAIT_LENGTH]).replace(PLACEHOLDER, HORIZONTAL_LINE);
   }
   
   private String getBestTimeStr(Duration bestTime) {
     return LocalTime.ofNanoOfDay(bestTime.toNanos()).format(FORMATTER);
   }
}
