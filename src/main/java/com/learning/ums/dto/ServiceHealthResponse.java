package com.learning.ums.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ServiceHealthResponse {

    private String message;
    private String since;
    private String timestamp;
}
