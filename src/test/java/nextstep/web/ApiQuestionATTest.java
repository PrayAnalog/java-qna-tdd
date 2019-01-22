package nextstep.web;

import nextstep.domain.Question;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiQuestionATTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void list() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/questions", String.class);
        System.out.println(response.getBody());
    }

    @Test
    public void create_show() {
        restTemplate = restTemplate.withBasicAuth("javajigi", "test");

        Question question = new Question("title", "contents");
        ResponseEntity<Void> response = restTemplate.postForEntity("/api/questions", question, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI location = response.getHeaders().getLocation();
        ResponseEntity<Question> questionResponse = restTemplate.getForEntity(location, Question.class);
        assertThat(questionResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        Question savedQuestion = questionResponse.getBody();
        assertThat(savedQuestion.getTitle()).isEqualTo(question.getTitle());
        assertThat(savedQuestion.getContents()).isEqualTo(question.getContents());
    }

    @Test
    public void create_show_rewrite() {
        restTemplate = restTemplate.withBasicAuth("javajigi", "test");

        Question question = new Question("title", "contents");
        ResponseEntity<Void> response = restTemplate.postForEntity("/api/questions", question, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI location = response.getHeaders().getLocation();
        ResponseEntity<Question> questionResponse = restTemplate.getForEntity(location, Question.class);
        assertThat(questionResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        Question savedQuestion = questionResponse.getBody();
        assertThat(savedQuestion.getTitle()).isEqualTo(question.getTitle());
        assertThat(savedQuestion.getContents()).isEqualTo(question.getContents());

        String changedContents = "blahblah";
        question.setContents(changedContents);
//

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Question> entity = new HttpEntity(question, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(location, HttpMethod.PUT, entity, String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        questionResponse = restTemplate.getForEntity(location, Question.class);
        savedQuestion = questionResponse.getBody();
        assertThat(savedQuestion.getContents()).isEqualTo(changedContents);

//        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
//        map.add("content","blahblah");
//        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
//        ResponseEntity<String> response = restTemplate.exchange("https://notify-api.line.me/api/notify", HttpMethod.POST, entity, String.class);








    }

}
