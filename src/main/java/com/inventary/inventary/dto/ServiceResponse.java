package com.inventary.inventary.dto;

import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@Data
public class ServiceResponse<T> {
    private T data;
    private String message;
    private String status;
    private List<String> errors;


    public static <T> ServiceResponse<T> success(T data) {
        ServiceResponse<T> response = new ServiceResponse<>();
        response.setData(data);
        response.setStatus("SUCCESS");
        response.setMessage("Operación exitosa");
        return response;
    }


    public static <T> ServiceResponse<T> success(String message) {
        ServiceResponse<T> response = new ServiceResponse<>();
        response.setStatus("SUCCESS");
        response.setMessage(message);
        return response;
    }


    public static <T> ServiceResponse<T> error(String message, List<String> errors) {
        ServiceResponse<T> response = new ServiceResponse<>();
        response.setStatus("ERROR");
        response.setMessage(message);
        response.setErrors(errors);
        return response;
    }

}