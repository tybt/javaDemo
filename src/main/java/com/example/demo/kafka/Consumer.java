package com.example.demo.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class Consumer {
//    @KafkaListener(groupId = "test2",topics = "hello2")
//    public void listener(ConsumerRecord<?, ?> record){
//        Optional.ofNullable(record.value())
//                .ifPresent(message -> {
//                    log.info("【+++++++++++++++++ record = {} 】", record);
//                    log.info("【+++++++++++++++++ message = {}】", message);
//                });
//
//    }

}
