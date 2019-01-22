package restapi;

import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.nio.charset.Charset;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class GithubApiTest {
    private RestTemplate restTemplate;

    @Before
    public void setup() {
        restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(
                new DefaultUriBuilderFactory("https://api.github.com"));
    }

    @Test
    public void getUser() {
        ResponseEntity<GithubUser> result = restTemplate.exchange
                ("/user",
                        HttpMethod.GET,
                        new HttpEntity<String>(defaultUser()),
                        GithubUser.class);
//                        String.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(result.getBody());
    }


    @Test
    public void getRateLimit() {
        ResponseEntity<Ratelimit> result = restTemplate.exchange
                ("/rate_limit",
                        HttpMethod.GET,
                        null,
                        Ratelimit.class);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(result.getBody());
    }

    @Test
    public void sendLineMessage() {
        restTemplate = new RestTemplate();
        HttpHeaders headers = lineUser();
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("message","Refactoring...");
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<String> response = restTemplate.exchange("https://notify-api.line.me/api/notify", HttpMethod.POST, entity, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(response.getBody());
    }


    private HttpHeaders lineUser() {
        return new HttpHeaders() {{
            set( "Authorization", "Bearer E1ubC3KdHXJYa7PBZvLoZGck17VcGz953VtpVzsWt0W" );
        }};
    }

    private HttpHeaders defaultUser() {
        return createHeaders("parkjaesung", "test1234!@");
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