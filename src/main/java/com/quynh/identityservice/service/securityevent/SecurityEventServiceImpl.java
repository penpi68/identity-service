package com.quynh.identityservice.service.securityevent;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quynh.identityservice.dto.securityevent.SecurityEventResponse;
import com.quynh.identityservice.entity.SecurityEvent;
import com.quynh.identityservice.mapper.SecurityEventMapper;
import com.quynh.identityservice.repository.SecurityEventRepository;

@Service
public class SecurityEventServiceImpl implements SecurityEventService {

    private final SecurityEventRepository securityEventRepository;
    private final SecurityEventMapper securityEventMapper;

    public SecurityEventServiceImpl(SecurityEventRepository securityEventRepository,
                                    SecurityEventMapper securityEventMapper) {
        this.securityEventRepository = securityEventRepository;
        this.securityEventMapper = securityEventMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SecurityEventResponse> getSecurityEvents() {
        List<SecurityEvent> securityEvents = securityEventRepository.findAllByOrderByIdAsc();
        return securityEventMapper.toSecurityEventResponseList(securityEvents);
    }

}
