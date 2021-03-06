package ru.netology.agafonovDanil;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Container
    public static GenericContainer<?> devapp = new GenericContainer<>("devapp").withExposedPorts(8080);
    @Container
    public static GenericContainer<?> prodapp = new GenericContainer<>("prodapp").withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        devapp.start();
        prodapp.start();
    }

    @Test
    void contextLoadsForDevapp() {
        System.out.println(devapp.getMappedPort(8080));
        ResponseEntity<String> forDevapp = restTemplate
                .getForEntity("http://localhost:" + devapp.getMappedPort(8080) + "profile", String.class);
        Assertions.assertEquals("Current profile is dev",forDevapp.getBody());
    }

    @Test
    void contextLoadsForProdapp() {
        System.out.println(prodapp.getMappedPort(8081));
        ResponseEntity<String> forProdapp = restTemplate
                .getForEntity("http://localhost:" + prodapp.getMappedPort(8081) + "profile", String.class);
        Assertions.assertEquals("Current profile is production",forProdapp.getBody());
    }

}