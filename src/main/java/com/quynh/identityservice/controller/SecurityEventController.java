package com.quynh.identityservice.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quynh.identityservice.dto.securityevent.SecurityEventResponse;
import com.quynh.identityservice.service.securityevent.SecurityEventService;

@RestController
@RequestMapping("/api/security")
public class SecurityEventController {

    private final SecurityEventService securityEventService;

    public SecurityEventController(SecurityEventService securityEventService) {
        this.securityEventService = securityEventService;
    }

    @GetMapping("/events/")
    public ResponseEntity<List<SecurityEventResponse>> getSecurityEvents() {
        List<SecurityEventResponse> response = securityEventService.getSecurityEvents();
        return ResponseEntity.ok(response);
    }

}
