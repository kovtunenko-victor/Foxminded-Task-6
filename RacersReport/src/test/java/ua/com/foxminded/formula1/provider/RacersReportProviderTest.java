package ua.com.foxminded.formula1.provider;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.times;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.com.foxminded.formula1.domain.Racer;
import ua.com.foxminded.formula1.reader.FileReaderImpl;
import ua.com.foxminded.formula1.validator.RacerValidator;

@ExtendWith(MockitoExtension.class)
class RacersReportProviderTest {
   @InjectMocks
   private RacersReportProvider reportProvider;

   @Mock
   private FileReaderImpl reader;

   @Mock
   private RacerValidator validator;

   @Mock
   private RacerParserProvider parcer;

   @Mock
   private RacerReportViewProvider reportViewer;

   @Test
   void provideStatisticsShouldReturnStringOfSortedRacersList() {
      final List<String> testList = Collections.singletonList("Test");

      final Racer racer = Racer.builder().withName("Test").withTeam("Test").build();

      when(reader.read("Test")).thenReturn(Arrays.asList("Test"));

      doNothing().when(validator).validate("Test", "Test", "Test");

      when(parcer.parse(testList, testList, testList)).thenReturn(Arrays.asList(racer));

      when(reportViewer.provideView(Collections.singletonList(racer)))
            .thenReturn("   1 Test Test           |Test Team                     |0:56.449");

      assertThat(reportProvider.provideStatistics("Test", "Test", "Test"), equalTo("   1 Test Test           |Test Team                     |0:56.449"));
      verify(validator).validate("Test", "Test", "Test");
      verify(parcer).parse(testList, testList, testList); 
      verify(reader, times(3)).read("Test");
      verify(reportViewer).provideView(Collections.singletonList(racer));
   }

   @Test
   void provideStatisticsShouldThrowExceptionInValidateMethod() {
      doThrow(new IllegalArgumentException("File path is null")).when(validator).validate(null, null, null);

      assertThrows(IllegalArgumentException.class, () -> reportProvider.provideStatistics(null, null, null));
      
      verify(validator).validate(null, null, null); 
      verifyNoMoreInteractions(parcer, reader, reportViewer);
   }
}
