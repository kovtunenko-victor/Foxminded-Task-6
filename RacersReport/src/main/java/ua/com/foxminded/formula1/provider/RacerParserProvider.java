package ua.com.foxminded.formula1.provider;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ua.com.foxminded.formula1.domain.Racer;
import ua.com.foxminded.formula1.utils.BestTimeCalculator;

public class RacerParserProvider implements ParserProvider<Racer> {
   private static final String UNDERSCORE = "_";

   private static final int ABBREVIATIONS_CODE_POSITION = 0;

   private static final int ABBREVIATIONS_NAME_POSITION = 1;

   private static final int ABBREVIATIONS_TEAM_POSITION = 2;

   private static final int START_DELIMITER_POSITION = 3;

   private static final int END_DELIMITER_POSITION = 3;

   private static final String DATE_FORMAT_STR = "yyyy-MM-dd_HH:mm:ss.SSS";
   
   private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern(DATE_FORMAT_STR);

   @Override
   public List<Racer> parse(List<String> abbreviationsLines, List<String> startLines, List<String> endLines) {
      final List<Racer> racers = new ArrayList<>();
      
      abbreviationsLines.forEach(abbreviationLine -> {
         String startFiltredLine = startLines.stream()
               .filter(startLine -> abbreviationLine.split(UNDERSCORE)[ABBREVIATIONS_CODE_POSITION]
                     .equals(startLine.substring(0, START_DELIMITER_POSITION)))
               .findAny().orElse(null);
         
         LocalDateTime startDateTime = null;
         
         if (startFiltredLine != null) {
            startDateTime = parseDate(startFiltredLine.substring(START_DELIMITER_POSITION));
         }

         String endFiltredLine = endLines.stream()
               .filter(endLine -> abbreviationLine.split(UNDERSCORE)[ABBREVIATIONS_CODE_POSITION]
                     .equals(endLine.substring(0, END_DELIMITER_POSITION)))
               .findAny().orElse(null);
         
         LocalDateTime endDateTime = null;
         
         if (endFiltredLine != null) {
            endDateTime = parseDate(endFiltredLine.substring(END_DELIMITER_POSITION));
         }
         
         Racer racer = Racer.builder()
               .withName(abbreviationLine.split(UNDERSCORE)[ABBREVIATIONS_NAME_POSITION])
               .withTeam(abbreviationLine.split(UNDERSCORE)[ABBREVIATIONS_TEAM_POSITION])
               .withDuration(BestTimeCalculator.calcBestTime(startDateTime, endDateTime))
               .build();
         
         racers.add(racer);

      });

      return racers.stream().sorted().collect(Collectors.toList());
   }

   private LocalDateTime parseDate(String date) {
      return LocalDateTime.parse(date, DATE_FORMAT);
   }

}
