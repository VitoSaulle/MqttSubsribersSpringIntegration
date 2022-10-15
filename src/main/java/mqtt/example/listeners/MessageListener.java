package mqtt.example.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.stereotype.Component;

import mqtt.example.configuration.TopicSubscribersConfiguration;
import mqtt.example.handlers.InboundMessageHandler;

@Component
public class MessageListener {

	@Autowired
	TopicSubscribersConfiguration topicSubribersConfig;

	@Autowired
	private InboundMessageHandler messageHandler;

	@Bean // Subscriber 1 message listener
	public IntegrationFlow listenerSubriber1() {
		return IntegrationFlows
				.from(topicSubribersConfig.subscriber1())
				.transform(p -> p)
				.handle(message -> {
					messageHandler.readingsHandler1(message.getHeaders(), message.getPayload().toString());
				})
				.get();
	}

	@Bean // Subscriber 2 message listener
	public IntegrationFlow listenerSubriber2() {
		return IntegrationFlows
				.from(topicSubribersConfig.subscriber2())
				.transform(p -> p)
				.handle(message -> {
					messageHandler.readingsHandler2(message.getHeaders(), message.getPayload().toString());
				})
				.get();
	}
}
