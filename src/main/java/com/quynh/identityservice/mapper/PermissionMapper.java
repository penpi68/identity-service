package com.quynh.identityservice.mapper;

import org.mapstruct.Mapper;

import com.quynh.identityservice.dto.request.PermissionRequest;
import com.quynh.identityservice.dto.response.PermissionResponse;
import com.quynh.identityservice.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
