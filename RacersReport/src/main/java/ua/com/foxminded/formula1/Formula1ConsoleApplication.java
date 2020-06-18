package ua.com.foxminded.formula1;

import ua.com.foxminded.formula1.domain.Racer;
import ua.com.foxminded.formula1.provider.ParserProvider;
import ua.com.foxminded.formula1.provider.RacerParserProvider;
import ua.com.foxminded.formula1.provider.RacerReportViewProvider;
import ua.com.foxminded.formula1.provider.RacersReportProvider;
import ua.com.foxminded.formula1.provider.ReportViewProvider;
import ua.com.foxminded.formula1.reader.FileReader;
import ua.com.foxminded.formula1.reader.FileReaderImpl;
import ua.com.foxminded.formula1.validator.RacerValidator;
import ua.com.foxminded.formula1.validator.Validator;

public class Formula1ConsoleApplication {
   private static final String PATH = "./src/main/resources/";
   
   private static final String ABBREVIATIONS = "abbreviations.txt";
   
   private static final String START = "start.log";
   
   private static final String END = "end.log";
   
   public static void main(String[] args) {
      FileReader reader = new FileReaderImpl();
      Validator validator = new RacerValidator();
      ParserProvider<Racer> parcer = new RacerParserProvider();
      ReportViewProvider<Racer> reportViewer = new RacerReportViewProvider();
      RacersReportProvider reportProvider = new RacersReportProvider(reader, validator, parcer, reportViewer);
      
      String result = reportProvider.provideStatistics(PATH + ABBREVIATIONS, PATH + START, PATH + END);
      System.out.println(result); 
   }
}
