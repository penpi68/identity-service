package com.quynh.identityservice.service.securityevent;

import java.util.List;

import com.quynh.identityservice.dto.securityevent.SecurityEventResponse;

public interface SecurityEventService {

    List<SecurityEventResponse> getSecurityEvents();

}
