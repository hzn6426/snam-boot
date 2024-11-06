package com.baomibing.snam.gateway.spi;

import com.baomibing.gateway.common.ApplicationContextHandler;
import com.baomibing.gateway.spi.BusinessTenantRoleService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auto.service.AutoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.text.MessageFormat;

/**
 * DefaultSpiBusinessTenantRoleService
 *
 * @author frog 2024/10/29 17:30
 * @version 1.0.0
 **/
@AutoService(value = BusinessTenantRoleService.class) @Slf4j
public class DefaultSpiBusinessTenantRoleService implements BusinessTenantRoleService {

    private  WebClient.Builder webClientBuilder() {
        return ApplicationContextHandler.getBean(WebClient.Builder.class);
    }

    public  String  makeGetRequest(String url) {
        final ObjectMapper mapper = new ObjectMapper()
                .findAndRegisterModules()
                .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        final ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs()
                .jackson2JsonDecoder(new Jackson2JsonDecoder(mapper)))
                .build();

        ReactorClientHttpConnector httpConnector = new ReactorClientHttpConnector(HttpClient.newConnection());
        // Error handling
        return webClientBuilder().exchangeStrategies(exchangeStrategies).clientConnector(httpConnector)
                .build().get()
                .uri(url)
                .headers(header -> {
                    header.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                }).exchangeToMono(clientResponse -> {
                    // Error handling
//                    System.out.println("status code is :" + clientResponse.statusCode());
                    return clientResponse.bodyToMono(String.class);
                }).doOnError(err -> log.error("do on err message:{}", err.getMessage())).block();

    }

    @Override
    public String getRolesByUrl(String tenantId, String reqUrl, String reqMethod) {
        String url = "http://snam-gateway/feign/authority/tenant/resource/getRolesByUrl?tid={0}&url={1}&m={2}";
        String uri = MessageFormat.format(url, tenantId, reqUrl, reqMethod);
        return makeGetRequest(uri);
    }
}
