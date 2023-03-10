package br.com.siecola.aws_project02.services;

import br.com.siecola.aws_project02.models.Envelope;
import br.com.siecola.aws_project02.models.ProductEvent;
import br.com.siecola.aws_project02.models.ProductEventLog;
import br.com.siecola.aws_project02.models.SnsMessage;
import br.com.siecola.aws_project02.repositories.ProducEventsLogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

@Service
public class ProductEventConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(ProductEventConsumer.class);
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProducEventsLogRepository producEventsLogRepository;

    public ProductEventConsumer() {
    }

    @JmsListener(destination = "${aws.sqs.queue.product.events.name}")
    public void receiveProductEvents(TextMessage textMessage) throws JMSException, IOException {

        SnsMessage snsMessage = objectMapper.readValue(textMessage.getText(), SnsMessage.class);

        Envelope envelope = objectMapper.readValue(snsMessage.getMessage(), Envelope.class);

        ProductEvent productEvent = objectMapper.readValue(envelope.getData(), ProductEvent.class);

        LOG.info("Product event received - Event: {} - ProductId: {} - MessageId: {} - ReceiveProductEvent: {}",
                envelope.getEventType(),
                productEvent.getProductId(),
                snsMessage.getMessageId(),
                textMessage.getText());

        ProductEventLog productEventLog = buildProductEventLog(envelope, productEvent, snsMessage.getMessageId());
        producEventsLogRepository.save(productEventLog);
    }

    private ProductEventLog buildProductEventLog(Envelope envelope, ProductEvent productEvent, String messageId) {

        Long timestamp = Instant.now().toEpochMilli();

        ProductEventLog productEventLog = new ProductEventLog();
        productEventLog.setEventType(envelope.getEventType());
        productEventLog.setMessageId(messageId);
        productEventLog.setProductId(productEvent.getProductId());
        productEventLog.setUsername(productEvent.getUsername());
        productEventLog.setTimestamp(timestamp);
        productEventLog.setTtl(Instant.now().plus(
                Duration.ofMinutes(10)
        ).getEpochSecond());

        return productEventLog;
    }
}
