package com.watchworthy.api.service.impl;

import com.watchworthy.api.repository.RoleRepository;
import com.watchworthy.api.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository _roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        _roleRepository = roleRepository;
    }
}
