package ua.com.foxminded.formula1.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReaderImpl implements FileReader {
   @Override
   public List<String> read(String path) {
      try (Stream<String> lines = Files.lines(Paths.get(path))) {
          return lines.collect(Collectors.toList());
      } catch (IOException ex) {
         throw new IllegalArgumentException("Error reading file " + path, ex);
      }
   }
}
