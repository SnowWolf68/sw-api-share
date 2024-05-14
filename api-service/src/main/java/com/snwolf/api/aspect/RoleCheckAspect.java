package com.snwolf.api.aspect;

import com.snwolf.api.annotation.CheckRole;
import com.snwolf.api.context.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class RoleCheckAspect {
    // 切点表达式抽取
    @Pointcut("execution(* com.snwolf.api.*.*(..)) && @annotation(com.snwolf.api.annotation.CheckRole)")
    public void checkRolePointCut(){}

    @Before("checkRolePointCut()")
    public void checkRole(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.info("当前切点为: {}", signature.getMethod().toString());
        CheckRole checkRole = signature.getMethod().getAnnotation(CheckRole.class);
        String[] role = checkRole.role();
        log.info("checkRole: {}", role);

        Long userId = BaseContext.getCurrentId();

    }
}
