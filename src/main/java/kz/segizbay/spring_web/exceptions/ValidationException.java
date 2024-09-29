package kz.segizbay.spring_web.exceptions;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ValidationException extends RuntimeException{
    private List<String> errorsFieldMessages;
    public ValidationException(List<String> errorsFieldMessages) {
        super(errorsFieldMessages.stream().collect(Collectors.joining(", ")));
        this.errorsFieldMessages = errorsFieldMessages;
    }
}
