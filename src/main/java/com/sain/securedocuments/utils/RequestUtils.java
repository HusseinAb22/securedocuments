package com.sain.securedocuments.utils;


import com.sain.securedocuments.domain.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static java.time.LocalTime.now;
import static org.apache.commons.lang3.StringUtils.*;

public class RequestUtils {
    public static Response getResponse(HttpServletRequest request, Map<?, ?> data, String message, HttpStatus status) {
        return new Response(now().toString(),status.value(),request.getRequestURI(), HttpStatus.valueOf(status.value()),message, EMPTY,data);
    }
}
