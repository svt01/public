package  com.example.httpserver.contract.base;

import com.example.httpserver.HttpServerApplication;
import com.example.httpserver.producer.ProducerController;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;


//@AutoConfigureMockMvc
//@AutoConfigureMessageVerifier
//@WebMvcTest(ProducerController.class)
//@ContextConfiguration(classes = {HttpServerApplication.class})
//@RunWith(SpringRunner.class)
//@DirtiesContext
@SpringBootTest(classes = HttpServerApplication.class)
public class BaseTestClass {

    @Autowired
    private ProducerController producerController;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(producerController);
    }


}