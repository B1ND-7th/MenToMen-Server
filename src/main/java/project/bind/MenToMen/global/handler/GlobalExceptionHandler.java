package project.bind.MenToMen.global.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.bind.MenToMen.global.error.CustomError;
import project.bind.MenToMen.global.error.ErrorResponseEntity;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomError.class)
    protected ResponseEntity handleCustomException(CustomError e){
        return ErrorResponseEntity.responseEntity(e.getErrorCode());
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    protected ErrorResponseEntity handleIndexException(Exception e){
        return ErrorResponseEntity.builder()
                .status(400)
                .code("BAD REQUEST")
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    protected ErrorResponseEntity handleException(Exception e){
        log.error(e.toString());
        return ErrorResponseEntity.builder()
                .status(500)
                .code("INTERNAL_SERVER_ERROR")
                .message(e.getMessage())
                .build();
    }
}
