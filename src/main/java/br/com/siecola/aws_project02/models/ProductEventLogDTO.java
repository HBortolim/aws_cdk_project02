package br.com.siecola.aws_project02.models;

import br.com.siecola.aws_project02.enums.EventType;

public class ProductEventLogDTO {
    private final String code;
    private final EventType eventType;

    private final String messageId;
    private final Long productId;
    private final String username;
    private final Long timestamp;

    public ProductEventLogDTO(ProductEventLog model) {
        this.code = model.getId();
        this.eventType = model.getEventType();
        this.productId = model.getProductId();
        this.username = model.getUsername();
        this.timestamp = model.getTimestamp();
        this.messageId = model.getMessageId();
    }

    public String getCode() {
        return code;
    }

    public EventType getEventType() {
        return eventType;
    }

    public String getMessageId() {
        return messageId;
    }

    public Long getProductId() {
        return productId;
    }

    public String getUsername() {
        return username;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}
