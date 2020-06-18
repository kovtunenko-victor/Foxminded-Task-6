package ua.com.foxminded.formula1.validator;

import java.io.File;

public class RacerValidator implements Validator {
   @Override
   public void validate(String abbreviationsFilePath, String startFilePath, String endFilePath) {
      validateFilename(abbreviationsFilePath, "Abbreviations file path");
      validateFilename(startFilePath, "Start file path");
      validateFilename(endFilePath, "End file path");

      validateFile(abbreviationsFilePath);
      validateFile(startFilePath);
      validateFile(endFilePath);
   }

   private static void validateFilename(String filename, String message) {
      if (filename == null || filename.isEmpty()) {
         throw new IllegalArgumentException(
               String.format("Path for [%s] is null or empty. File path [%s]", message, filename));
      }
   }

   private static void validateFile(String filename) {
      File file = new File(filename);

      if (!file.exists()) {
         throw new IllegalArgumentException(String.format("File [%s] is not exists. File path [%s]", file.getName(), filename)); 
      }

      if (file.length() == 0) {
         throw new EmptyFileContextRuntimeException(String.format("File [%s] is empty. File path [%s]", file.getName(), filename));
      }
   }
}
