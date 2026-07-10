package com.walid.educore_api.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record ValidationErrorResponse(

        LocalDateTime timestamp,

        int status,

        String message,

        Map<String, List<String>> errors

) {
}