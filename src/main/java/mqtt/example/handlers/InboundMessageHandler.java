package mqtt.example.handlers;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class InboundMessageHandler {

	@ServiceActivator
	public void readingsHandler1(@Header MessageHeaders header, @Payload String payload) {

		try {
			var topic = header.get("mqtt_receivedTopic");

			log.info("\n********* ARRIVED MESSAGE TO HANDLER 1: *************\nTOPIC: " + topic + "\nHEADER: " + header
					+ "\nPAYLOAD: " + payload + "\n********** END OF MESSAGE TO HANDLER 1 ************\n");

		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@ServiceActivator
	public void readingsHandler2(@Header MessageHeaders header, @Payload String payload) {

		try {
			var topic = header.get("mqtt_receivedTopic");

			log.info("\n********* ARRIVED MESSAGE TO HANDLER 2: *************\nTOPIC: " + topic + "\nHEADER: " + header
					+ "\nPAYLOAD: " + payload + "\n********** END OF MESSAGE TO HANDLER 2 ************\n");

		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
