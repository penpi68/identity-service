package com.quynh.identityservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.quynh.identityservice.dto.request.RoleRequest;
import com.quynh.identityservice.dto.response.RoleResponse;
import com.quynh.identityservice.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
