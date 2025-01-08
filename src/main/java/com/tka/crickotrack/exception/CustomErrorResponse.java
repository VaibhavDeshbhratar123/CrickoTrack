package com.tka.crickotrack.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorResponse {

    private String error;
    private String message;
    private String field;
    private Object rejectedValue;
}
