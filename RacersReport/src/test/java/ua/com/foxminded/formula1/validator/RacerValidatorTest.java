package ua.com.foxminded.formula1.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.jupiter.api.Test;

class RacerValidatorTest {
   private final Validator validator = new RacerValidator();
   
   private static final String EMPTY_FILE_PATH = "./src/test/resources/empty.txt";
   
   private static final String NO_EXISTS_FILE_PATH = "./src/test/resources/super.txt";
   
   private static final String START_FILE_PATH = "./src/test/resources/start.log";
   
   @Test
   void validateShouldThrowExceptionIfAnyFilePathIsNull() {
      Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
         validator.validate(null, START_FILE_PATH, START_FILE_PATH);
      });

      assertThat(exception.getMessage(), equalTo("Path for [Abbreviations file path] is null or empty. File path [null]"));
   }
   
   @Test
   void validateShouldThrowExceptionIfAnyFilePathIsEmpty() {
      Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
         validator.validate(START_FILE_PATH, "", START_FILE_PATH);
      });

      assertThat(exception.getMessage(), equalTo("Path for [Start file path] is null or empty. File path []"));
   }  
   
   @Test
   void validateShouldThrowExceptionIfAnyFileIsNotExists() {
      Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
         validator.validate(START_FILE_PATH, NO_EXISTS_FILE_PATH, START_FILE_PATH);
      });

      assertThat(exception.getMessage(), equalTo("File [super.txt] is not exists. File path [./src/test/resources/super.txt]"));
   }  
   
   @Test
   void validateShouldThrowExceptionIfAnyFileIsEmpty() {
      Throwable exception = assertThrows(EmptyFileContextRuntimeException.class, () -> {
         validator.validate(START_FILE_PATH, START_FILE_PATH, EMPTY_FILE_PATH);
      });

      assertThat(exception.getMessage(), equalTo("File [empty.txt] is empty. File path [./src/test/resources/empty.txt]"));
   }
   
   @Test
   void validateShouldDoNotThrowExceptionIfAllFileIsNotEmpty() {
      assertDoesNotThrow(() -> validator.validate(START_FILE_PATH, START_FILE_PATH, START_FILE_PATH));
   }  
}
