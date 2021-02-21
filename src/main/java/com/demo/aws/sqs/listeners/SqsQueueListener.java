package com.demo.aws.sqs.listeners;

import com.amazonaws.services.sqs.model.Message;
import com.demo.aws.sqs.Constants;
import com.demo.aws.sqs.Payload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sqs")
@Slf4j
public class SqsQueueListener {

    @SqsListener(value = Constants.QUEUE_NAME, deletionPolicy = SqsMessageDeletionPolicy.ALWAYS)
    public void getMessageFromSqs(Message message, @Header("MessageId") String messageID) throws JsonProcessingException {
        log.info("Obtained message with message id {} from queue {}", messageID, Constants.QUEUE_NAME);
        Payload payload = new ObjectMapper().readValue(message.getBody(), Payload.class);
        log.info("Payload Id: {} and text: {}", payload.getId(), payload.getText());
    }
}
