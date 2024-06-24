package com.sbm.mc.sevenroomstoreviewpro.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sbm.mc.sevenroomstoreviewpro.service.ReviewproService;
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
public class ReviewproServiceImpl implements ReviewproService {


    private final Logger log = LoggerFactory.getLogger(ReviewproServiceImpl.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    SevenroomsTokenService sevenroomsTokenService;

    @Value(value = "${sevenroomsApi.graviteeUrl}")
    private String graviteeUrl;

    @Value(value = "${sevenroomsApi.api-key}")
    private String graviteeApiKey;

    @GetMapping("/exportReservations")
    public String getReservationsFromSevenRoomsApi(
        @RequestParam(required = false) String pmsId,
        @RequestParam(required = false) String firstName,
        @RequestParam(required = false) String lastName,
        @RequestParam(required = false) String language,
        @RequestParam(required = false) String checkin,
        @RequestParam(required = false) String checkout,
        @RequestParam(required = false) String email,
        @RequestParam(required = false) String authToken

    ) {
        if (authToken == null) {
            authToken = sevenroomsTokenService.generateToken();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-7rooms-Authorization", authToken);
        headers.set("X-Api-Key", graviteeApiKey);

        String reviewproUrl = graviteeUrl + "/reviewpro/v1/v1/pms/guests/58887da09932021ab31c5540";

        StringBuilder queryString = new StringBuilder();
        queryString.append("?pmsId=").append(pmsId);
        putIfNotNull(queryString, "firstName", firstName);
        putIfNotNull(queryString, "lastName", lastName);
        putIfNotNull(queryString, "language", language);
        putIfNotNull(queryString, "checkin", checkin);
        putIfNotNull(queryString, "checkout", checkin);
        putIfNotNull(queryString, "email", email);


        reviewproUrl += queryString.toString();

        HttpEntity<String> entity = new HttpEntity<>(headers);

        if (!authToken.isEmpty()) {
            log.info("Sending Post request to: {}", reviewproUrl);
            try {
                ResponseEntity<String> response = restTemplate.exchange(reviewproUrl, HttpMethod.POST, entity, String.class);

                if (response.getStatusCode().is2xxSuccessful()) {
                    log.info("Received response with status code: {}", response.getStatusCode().value());
                    //                        clientsAndReservationsService.appendSearchFields(response.getBody(), "reservation");
                    System.out.println("");
                    // log.debug(response.getBody());
                    return response.getBody();
                } else {
                    return "Error: " + response.getStatusCode().value();
                }
            } catch (Exception e) {
                log.error("Error occurred while making GET request to {}: {}", reviewproUrl, e.getMessage());
                return "Error occurred while making GET request to " + reviewproUrl + e.getMessage();
            }
        } else {
            return "Error: Token empty , Request won't be sent.";
        }
    }

    public void putIfNotNull(StringBuilder queryString, String key, String value) {
        if (value != null) {
            queryString.append("&").append(key).append("=").append(value);
        }
    }
}
