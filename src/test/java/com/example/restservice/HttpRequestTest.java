package com.example.restservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnRightMessage() {
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/" + "task?number=123&side=<", String.class))
                .contains("number that is <");
    }

    @Test
    public void shouldReturnErrorMessage() {
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/" + "task?number=1000001&side=<", String.class))
                .isEqualTo("{\"message\":\"Too big num\"}");
    }

    @Test
    public void shouldReturnErrorMessage2() {
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/" + "task?number=null&side=<", String.class))
                .isEqualTo("{\"message\":\"Wrong params\"}");
    }
}