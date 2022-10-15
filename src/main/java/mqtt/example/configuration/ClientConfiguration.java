package mqtt.example.configuration;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;

@Configuration
public class ClientConfiguration {

	@Value("${mqtt.configuration.broker.protocol}")
	private String brokerProtocol;

	@Value("${mqtt.configuration.broker.address}")
	private String brokerAddress;

	@Value("${mqtt.configuration.broker.port}")
	private String brokerPort;

	@Value("${mqtt.configuration.client.username}")
	private String username;

	@Value("${mqtt.configuration.client.password}")
	private String password;


	@Bean
	public MqttPahoClientFactory mqttClientFactory() {
		DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
		MqttConnectOptions options = new MqttConnectOptions();
		options.setServerURIs(new String[] { String.format("%s://%s:%s", brokerProtocol, brokerAddress, brokerPort) });
		options.setUserName(username);
		options.setPassword(password.toCharArray());
		factory.setConnectionOptions(options);
		return factory;
	}


}
