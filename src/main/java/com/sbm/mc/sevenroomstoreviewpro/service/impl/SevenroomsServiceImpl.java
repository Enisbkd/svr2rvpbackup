package com.sbm.mc.sevenroomstoreviewpro.service.impl;

import com.sbm.mc.sevenroomstoreviewpro.service.SevenroomsTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Service
public class SevenroomsServiceImpl {

    private static final Logger log = LoggerFactory.getLogger(SevenroomsServiceImpl.class);

    @Autowired
    SevenroomsTokenService sevenroomsTokenService;

    @Autowired
    RestTemplate restTemplate;

    @Value(value = "${sevenroomsApi.graviteeUrl}")
    private String graviteeUrl;

    @Value(value = "${sevenroomsApi.api-key}")
    private String graviteeApiKey;

    @Value(value = "${sevenroomsApi.venueGroupId}")
    private String venueGroupId;

    @GetMapping(path = "/exportClients", produces = "application/json")
    public String getClientFromSevenRoomsApi(
        @RequestParam(required = true) String client_id,
        @RequestParam(required = false) String authToken
    ) {
        String url = graviteeUrl + "clients";
        if (authToken == null) {
            authToken = sevenroomsTokenService.generateToken();
        }


        HttpHeaders headers = new HttpHeaders();
        headers.set("X-7rooms-Authorization", authToken);
        headers.set("X-Api-Key", graviteeApiKey);

        StringBuilder query = new StringBuilder();
        query.append("/").append(client_id);

        url += query.toString();

        HttpEntity<String> entity = new HttpEntity<>(headers);

        if (!authToken.isEmpty()) {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                log.info("Received response with status code: {}", response.getStatusCode().value());
                System.out.println("hi");
                return response.getBody();
            } else {
                return "Error: " + response.getStatusCode().value();
            }
        } else {
            return "Error: Token empty , Request won't be sent.";
        }
    }
}
