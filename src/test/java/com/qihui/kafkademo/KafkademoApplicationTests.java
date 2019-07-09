package com.qihui.kafkademo;

import com.qihui.kafkademo.provider.KafkaSender;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.time.Duration;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkademoApplicationTests {

    @Autowired
    private KafkaSender kafkaSender;

    @Autowired
    private KafkaAutoConfiguration kafkaProperties;

    private Consumer kafkaConsumer;

    @Before
    public void before() {
        kafkaConsumer = kafkaProperties.kafkaConsumerFactory().createConsumer();
    }

    @Test
    public void sendMessage() {
        kafkaSender.send("test", "this is a text message");
        kafkaConsumer.subscribe(Pattern.compile("test"));
        ConsumerRecords poll = kafkaConsumer.poll(Duration.ofSeconds(3));
        Iterable test = poll.records("test");
        ConsumerRecord record = (ConsumerRecord) test.iterator().next();
        Assert.isTrue(record.value().toString().contains("message"), "there is no message");
    }

}
