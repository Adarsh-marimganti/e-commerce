package com.projects.productservice.commons;

import com.projects.productservice.dtos.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthCommons {
    private RestTemplate restTemplate;

    public AuthCommons (RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto validateToken(String tokenValue) {
        ResponseEntity<UserDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/users/validate/" + tokenValue,
                UserDto.class);
        if(responseEntity.getBody() == null) {
            // throw an exception
            return null;
        }
        return responseEntity.getBody();
    }
}
