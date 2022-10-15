package mqtt.example.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;

@Configuration
public class TopicSubscribersConfiguration {

	@Autowired
	ClientConfiguration clientConfig;

	@Value("${mqtt.configuration.broker.topic1}")
	private String brokerTopic1;

	@Value("${mqtt.configuration.broker.qos1}")
	private int brokerQos1;

	@Value("${mqtt.configuration.broker.topic2}")
	private String brokerTopic2;

	@Value("${mqtt.configuration.broker.qos2}")
	private int brokerQos2;

	@Value("${mqtt.configuration.inbound.clientid}")
	private String inboundClientId;

	// First Subcriber
	@Bean
	public MessageProducerSupport subscriber1() {
		var adapter = new MqttPahoMessageDrivenChannelAdapter(inboundClientId, clientConfig.mqttClientFactory(),
				brokerTopic1);
		adapter.setCompletionTimeout(5000);
		adapter.setConverter(new DefaultPahoMessageConverter());
		adapter.setQos(brokerQos1);
		return adapter;
	}

	// Second Subscriber
	@Bean
	public MessageProducerSupport subscriber2() {
		var adapter = new MqttPahoMessageDrivenChannelAdapter(inboundClientId, clientConfig.mqttClientFactory(),
				brokerTopic2);
		adapter.setCompletionTimeout(5000);
		adapter.setConverter(new DefaultPahoMessageConverter());
		adapter.setQos(brokerQos2);
		return adapter;
	}
}
