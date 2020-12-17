package com.todo.aop;


import com.todo.model.entity.User;
import com.todo.service_abstract.RoleService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Aspect
@Component
public class SetterAspect {
    private final RoleService roleService;

    public SetterAspect(RoleService roleService) {
        this.roleService = roleService;
    }

    @Pointcut("execution(public * com.todo.controller.rest.RegistryRestController.registryUser(..) )")
    public void changeRegistryController() {
    }

    @Before("changeRegistryController()")
    public void changeRegistryControllerRelease(JoinPoint jp) {
        User user = (User) jp.getArgs()[0];
        user.setRoles(Collections.singleton(roleService.getRoleByName("USER")));
    }
}
