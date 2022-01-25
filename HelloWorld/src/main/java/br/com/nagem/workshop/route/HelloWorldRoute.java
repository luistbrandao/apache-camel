package br.com.nagem.workshop.route;

import org.apache.camel.BeanInject;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import br.com.nagem.workshop.processor.HealthCheckResponseProcessor;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "HelloWord")
@Component
public class HelloWorldRoute extends RouteBuilder {
	
	@BeanInject
	private HealthCheckResponseProcessor healthCheckProcessor;
	
	
	private JacksonDataFormat createCustomerRequest;
	private JacksonDataFormat createCustomerresponse;
	
	public HelloWorldRoute() {
		createCustomerRequest = new JacksonDataFormat();  
		createCustomerresponse = new JacksonDataFormat();	
	}
	
	@Value("${custumer-service.url}")
	private String url;

	@Override
	public void configure() throws Exception {
		log.info("Inicio configure");
		restConfiguration().component("servlet").bindingMode(RestBindingMode.json);
		
		
		rest()
			.get("/")
				.consumes(MediaType.APPLICATION_JSON_VALUE)
				.route()
				    .log("mensagem antes: ${body}")
					.process(healthCheckProcessor)
					.log("mensagem depois: ${body}")
			.endRest();		
		
		rest()
			.post("/customer/create")
				.consumes(MediaType.APPLICATION_JSON_VALUE)
				.produces(MediaType.APPLICATION_JSON_VALUE)
				.route()
				    .setHeader(Exchange.HTTP_METHOD, constant("POST"))
						.log("Entrada de create custumer: ${body}")
						.marshal(createCustomerRequest)
						.to(url)
						.unmarshal(createCustomerresponse)
						.log("retorno : ${body}")
			.end();
		
		
	}

}
