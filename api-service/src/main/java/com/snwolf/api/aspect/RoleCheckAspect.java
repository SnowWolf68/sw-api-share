package com.snwolf.api.aspect;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.snwolf.api.annotation.CheckRole;
import com.snwolf.api.context.BaseContext;
import com.snwolf.api.domain.entity.User;
import com.snwolf.api.exception.RoleException;
import com.snwolf.api.result.Result;
import com.snwolf.api.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class RoleCheckAspect {

    private final IUserService userService;

    // 切点表达式抽取
    @Pointcut("execution(* com.snwolf.api.*.*.*(..)) && @annotation(com.snwolf.api.annotation.CheckRole)")
    public void checkRolePointCut(){}

    @Around("checkRolePointCut()")
    public Object checkRole(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.info("当前切点为: {}", signature.getMethod().toString());
        CheckRole checkRole = signature.getMethod().getAnnotation(CheckRole.class);
        String[] roleArr = checkRole.role();
        // 对role进行非空过滤
        List<String> roleList = Arrays.stream(roleArr).filter(StrUtil::isNotBlank).collect(Collectors.toList());
        log.info("checkRole: {}", roleList);
        if(CollectionUtil.isEmpty(roleList)){
            return joinPoint.proceed();
        }
        Long userId = BaseContext.getCurrentId();
        User user = userService.getById(userId);
        String userRole = user.getUserRole();
        boolean isMatch = false;
        for (String role : roleList) {
            if(role.equals(userRole)){
                isMatch = true;
                break;
            }
        }
        if(!isMatch){
            log.info("用户: {} 无权限访问", userId);
            return Result.error("用户无权限访问");
        }else {
            return joinPoint.proceed();
        }
    }
}
