package br.com.siecola.aws_project02.models;

import br.com.siecola.aws_project02.enums.EventType;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import org.springframework.data.annotation.Id;

@DynamoDBTable(tableName = "product-events")
public class ProductEventLog {
    @DynamoDBHashKey
    private String id;
    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "eventType")
    private EventType eventType;
    @DynamoDBAttribute(attributeName = "messageId")
    private String messageId;
    @DynamoDBAttribute(attributeName = "productId")
    private Long productId;
    @DynamoDBAttribute(attributeName = "username")
    private String username;
    @DynamoDBAttribute(attributeName = "timestamp")
    private Long timestamp;
    @DynamoDBAttribute(attributeName = "ttl")
    private Long ttl;

    public ProductEventLog() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getTtl() {
        return ttl;
    }

    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }
}
