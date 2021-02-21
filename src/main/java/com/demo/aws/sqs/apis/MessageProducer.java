package com.demo.aws.sqs.apis;

import com.amazonaws.services.sqs.model.Message;
import com.demo.aws.sqs.Constants;
import com.demo.aws.sqs.Payload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
@Slf4j
public class MessageProducer {

    private final QueueMessagingTemplate queueMessagingTemplate;

    @Autowired
    public MessageProducer(QueueMessagingTemplate queueMessagingTemplate) {
        this.queueMessagingTemplate = queueMessagingTemplate;
    }

    @PostMapping
    public void sendMessageToSqs(@RequestBody Payload payload) throws JsonProcessingException {
        log.info("Send message to sqs...");
        String payloadText = new ObjectMapper().writeValueAsString(payload);
        Message message = new Message();
        message.withBody(payloadText);
        queueMessagingTemplate.convertAndSend(Constants.QUEUE_NAME, message);
    }
}
