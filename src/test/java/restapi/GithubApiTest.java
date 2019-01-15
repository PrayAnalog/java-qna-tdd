package restapi;

import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.nio.charset.Charset;

public class GithubApiTest {
    private RestTemplate restTemplate;

    @Before
    public void setup() {
        restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(
                new DefaultUriBuilderFactory("https://api.github.com"));
    }

    @Test
    public void rate_limit() {
        ResponseEntity<String> result = restTemplate.exchange
                ("/rate_limit",
                        HttpMethod.GET,
                        new HttpEntity<String>(defaultUser()),
                        String.class);
        System.out.println(result.getBody());
    }

    private HttpHeaders defaultUser() {
        return createHeaders("parkjaesung", "test");
    }

    private HttpHeaders createHeaders(String username, String password){
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
        }};
    }
}

