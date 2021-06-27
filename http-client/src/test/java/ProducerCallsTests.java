import com.example.httpclient.HttpClientApplication;
import com.example.httpclient.consumer.ConsumerController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerExtension;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.assertj.core.api.BDDAssertions;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


@SpringBootTest(classes= HttpClientApplication.class, webEnvironment = WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = {"com.example:http-server:0.0.1-SNAPSHOT:stubs:9099"}, stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class ProducerCallsTests {

//    @RegisterExtension
//    public StubRunnerExtension stubRunner = new StubRunnerExtension()
//            .downloadStub("com.example", "http-server", "0.0.1-SNAPSHOT", "stubs")
//            .withPort(9091)
//            .stubsMode(StubRunnerProperties.StubsMode.LOCAL);

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void callToProducerTest() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:9099/prod/process" , String.class);
        System.out.println("response :: "+response.getBody());
        Assertions.assertEquals("Successful process response", response.getBody());
    }
}