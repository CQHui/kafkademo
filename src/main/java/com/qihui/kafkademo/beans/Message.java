package com.qihui.kafkademo.beans;

import lombok.Data;

import java.util.Date;

/**
 * @author chenqihui
 * @date 2019/7/7
 */
@Data
public class Message {
    private Long id;
    private String msg;
    private Date sendTime;
}
