package com.quynh.identityservice.mapper;


import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.quynh.identityservice.dto.securityevent.SecurityEventResponse;
import com.quynh.identityservice.entity.SecurityEvent;

@Mapper(componentModel = "spring")
public interface SecurityEventMapper {

    @SuppressWarnings("unused")
    @Mapping(target = "eventId", source = "id")
    SecurityEventResponse toSecurityEventResponse(SecurityEvent securityEvent);

    List<SecurityEventResponse> toSecurityEventResponseList(List<SecurityEvent> securityEvents);

}
