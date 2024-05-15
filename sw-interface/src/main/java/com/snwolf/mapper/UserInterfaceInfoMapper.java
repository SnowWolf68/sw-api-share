package com.snwolf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.snwolf.domain.entity.UserInterfaceInfo;
import org.apache.ibatis.annotations.Update;

public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {

    @Update("update user_interface_info set left_cnt = left_cnt - 1 where user_id = #{userId} and interface_id = #{interfaceId}")
    void deduckCnt(Long userId, Long interfaceId);
}
