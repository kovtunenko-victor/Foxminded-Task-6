package ua.com.foxminded.formula1.provider;

import java.util.List;

public interface ReportViewProvider<T> {
   String provideView(List<T> items);
}
