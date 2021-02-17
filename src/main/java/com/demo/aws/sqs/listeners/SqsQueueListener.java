package com.demo.aws.sqs.listeners;

import com.amazonaws.services.sqs.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sqs")
@Slf4j
public class SqsQueueListener {

    private static final String QUEUE_NAME = "<QUEUE NAME>";

    private final QueueMessagingTemplate queueMessagingTemplate;

    @Autowired
    public SqsQueueListener(QueueMessagingTemplate queueMessagingTemplate) {
        this.queueMessagingTemplate = queueMessagingTemplate;
    }

    @PostMapping(value = "/send")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void sendMessageToSqs(@RequestBody final Message message) {
        log.info("Sending the message to the Amazon sqs.");
        queueMessagingTemplate.convertAndSend(QUEUE_NAME, message);
        log.info("Message sent successfully to the Amazon sqs.");
    }

    @SqsListener(value = QUEUE_NAME, deletionPolicy = SqsMessageDeletionPolicy.ALWAYS)
    public void getMessageFromSqs(Message message, @Header("MessageId") String messageID) {
        log.info("Obtained message {} with message id {} from queue {}", message, messageID, QUEUE_NAME);
    }
}
