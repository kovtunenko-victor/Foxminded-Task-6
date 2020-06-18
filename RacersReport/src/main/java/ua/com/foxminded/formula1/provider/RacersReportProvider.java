package ua.com.foxminded.formula1.provider;

import java.util.List;

import ua.com.foxminded.formula1.domain.Racer;
import ua.com.foxminded.formula1.reader.FileReader;
import ua.com.foxminded.formula1.validator.Validator;

public class RacersReportProvider {
   private final FileReader fileReader;

   private final Validator validator;

   private final ParserProvider<Racer> parser;

   private final ReportViewProvider<Racer> reportViewer;

   public RacersReportProvider(FileReader fileReader, Validator validator, ParserProvider<Racer> parcer,
         ReportViewProvider<Racer> reportViewer) {
      this.fileReader = fileReader;
      this.validator = validator;
      this.parser = parcer;
      this.reportViewer = reportViewer;
   }

   public String provideStatistics(String abbreviationsFilePath, String startFilePath, String endFilePath) {
      validator.validate(abbreviationsFilePath, startFilePath, endFilePath);

      final List<String> abbreviationsFileLines = fileReader.read(abbreviationsFilePath);
      final List<String> startFileLines = fileReader.read(startFilePath);
      final List<String> endFileLines = fileReader.read(endFilePath);
      
      final List<Racer> racers = parser.parse(abbreviationsFileLines, startFileLines, endFileLines);

      return reportViewer.provideView(racers);
   }
}
