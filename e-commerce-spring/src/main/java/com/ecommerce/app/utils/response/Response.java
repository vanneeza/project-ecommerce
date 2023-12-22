package com.ecommerce.app.utils.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
    private String message;
    private Integer code;
    private String status;
    private T data;

    public static <T> ResponseEntity<Response<T>>response(String message, Integer code, String status, T data) {
        Response<T> response = new Response<>();
        response.setMessage(message);
        response.setCode(code);
        response.setStatus(status);
        response.setData(data);

        return ResponseEntity.status(code).contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
