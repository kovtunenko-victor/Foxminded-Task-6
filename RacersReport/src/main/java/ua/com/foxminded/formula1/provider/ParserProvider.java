package ua.com.foxminded.formula1.provider;

import java.util.List;

public interface ParserProvider <T> {
   List<T> parse(List<String> abbreviationsLines, List<String> startLines, List<String> endLines);
}
