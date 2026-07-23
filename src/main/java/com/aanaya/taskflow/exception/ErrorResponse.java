package com.aanaya.taskflow.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private Integer errorCode;
    private String errorMessage;
}
