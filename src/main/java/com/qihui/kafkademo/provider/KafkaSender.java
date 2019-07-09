package com.qihui.kafkademo.provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qihui.kafkademo.beans.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * @author chenqihui
 * @date 2019/7/7
 */
@Component
@Slf4j
public class KafkaSender {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private Gson gson = new GsonBuilder().create();
    //发送消息方法
    public void send() {
        send("default", UUID.randomUUID().toString());
    }


    public void send(String topic, String msg) {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(msg);
        message.setSendTime(new Date());
        log.info("+++++++++++++++++++++  message = {}", gson.toJson(message));
        kafkaTemplate.send(topic, gson.toJson(message));
    }
}
