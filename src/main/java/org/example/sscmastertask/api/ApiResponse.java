package org.example.sscmastertask.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
@AllArgsConstructor
public class ApiResponse {
    private HttpStatus status;
    private int code;
    private Map<String, String> message;
}
