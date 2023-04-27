package com.watchworthy.api.controller;

import com.watchworthy.api.service.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleService _roleService;
    public RoleController(RoleService roleService) {
        _roleService = roleService;
    }
    ///
}
