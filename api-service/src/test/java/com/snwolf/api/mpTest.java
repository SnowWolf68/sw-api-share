package com.snwolf.api;

import com.snwolf.api.domain.entity.User;
import com.snwolf.api.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

@SpringBootTest
@Slf4j
@RequiredArgsConstructor
public class mpTest {

    private final IUserService userService;

    @Test
    void test(){
        User user = User.builder()
                .userAccount("zhangsan")
                .password(DigestUtils.md5DigestAsHex("123".getBytes()))
                .build();
        userService.save(user);
        log.info("userId: {}", user.getId());
    }

}
