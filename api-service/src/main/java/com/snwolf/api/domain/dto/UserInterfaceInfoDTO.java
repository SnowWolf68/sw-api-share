package com.snwolf.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInterfaceInfoDTO {
    private Long userId;
    private Long interfaceId;
    private Integer totalCnt;
}
