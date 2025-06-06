package com.project.platform.dto;

import lombok.Data;

@Data
public class MessagesNotifyDTO {
    /**
     * 类型 1 房间  2 消息
     */
    private Integer type;
    private Object data;
}
