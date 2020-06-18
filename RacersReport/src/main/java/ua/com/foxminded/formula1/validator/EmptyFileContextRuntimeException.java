package ua.com.foxminded.formula1.validator;

public class EmptyFileContextRuntimeException extends RuntimeException {
   private static final long serialVersionUID = 1L;
   
   public EmptyFileContextRuntimeException(String detailMessage) {
      super(detailMessage);
   }
}
