package ua.com.foxminded.formula1.reader;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

import java.util.List;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Test;

class FileReaderImplTest {
   
   private static final String PATH = "./src/test/resources/";
   
   private static final String ABBREVIATIONS = "abbreviations.txt";
   
   private final FileReader reader = new FileReaderImpl();
   
   @Test
   void readShouldReturnCollectionOfAbbreviationsIfInputFileIsAbbreviations() { 
      List<String> data = reader.read(PATH + ABBREVIATIONS);
      
      assertThat(data, not(IsEmptyCollection.empty()));  
      assertThat(data, hasSize(2)); 
      assertThat(data, contains("NHR_Nico Hulkenberg_RENAULT", "SVF_Sebastian Vettel_FERRARI"));
   }
   
   @Test
   void readShouldThrowExeptionIfInputFileNotFound() {  
      Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
         reader.read(PATH);
      });

      assertThat(exception.getMessage(), equalTo("Error reading file ./src/test/resources/"));
   }
}
