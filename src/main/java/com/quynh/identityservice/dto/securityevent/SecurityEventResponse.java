package com.quynh.identityservice.dto.securityevent;

import java.time.LocalDateTime;

import com.quynh.identityservice.entity.SecurityAction;
import com.fasterxml.jackson.annotation.JsonProperty;

public record SecurityEventResponse(
        @JsonProperty("id")
        Long eventId,
        LocalDateTime date,
        SecurityAction action,
        String subject,
        String object,
        String path
) {
}
