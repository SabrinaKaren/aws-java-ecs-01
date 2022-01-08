package br.com.sabrina.aws_project01.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import br.com.sabrina.aws_project01.enums.EventType;
import br.com.sabrina.aws_project01.model.Envelope;
import br.com.sabrina.aws_project01.model.ProductEvent;
import br.com.sabrina.aws_project01.model.ProductTest;

@Service
public class ProductPublisher {

    private static final Logger LOG = LoggerFactory.getLogger(ProductPublisher.class);

    private AmazonSNS snsClient;
    private Topic productEventsTopic;
    private ObjectMapper objectMapper;

    public ProductPublisher(AmazonSNS snsClient,
            @Qualifier("productEventsTopic") Topic productEventsTopic,
            ObjectMapper objectMapper) {
        this.snsClient = snsClient;
        this.productEventsTopic = productEventsTopic;
        this.objectMapper = objectMapper;
    }

    public void publishProductEvent(ProductTest product, EventType eventType, String username) {
        
        ProductEvent productEvent = new ProductEvent();
        productEvent.setProductId(product.getId());
        productEvent.setCode(product.getCode());
        productEvent.setUsername(username);

        Envelope envelope = new Envelope();
        envelope.setEventType(eventType);

        try {
            envelope.setData(objectMapper.writeValueAsString(productEvent));
            snsClient.publish(productEventsTopic.getTopicArn(), objectMapper.writeValueAsString(envelope));
        } catch (JsonProcessingException e) {
            LOG.error("Failed to create product event message");
        }

    }

}