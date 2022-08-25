package project.bind.MenToMen.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class Response {

    private int status;
    private String message;

    public static Response of(HttpStatus status, String message){
        return new Response(status.value(), message);
    }

    public static ResponseEntity<Response> ok(String message){
        return new ResponseEntity<>(Response.of(HttpStatus.OK, message),HttpStatus.OK);
    }
}
