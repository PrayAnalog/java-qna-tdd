package nextstep.web;

import nextstep.domain.Question;
import nextstep.domain.User;
import nextstep.security.LoginUser;
import nextstep.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping("/api/questions")
public class ApiQuestionController {

    @Autowired
    private QnaService qnaService;

    @PutMapping("/{id}")
    public ResponseEntity<Object> edit(@LoginUser User loginUser, @PathVariable long id, @RequestBody Question question) {
        Question targetQuestion = qnaService.findById(id);
//        targetQuestion.setContents(question.getContents());
        qnaService.update(loginUser, id, question);
        return ResponseEntity.ok().build();
//                .created(URI.create("/api/questions/" + savedQuestion.getId()))
//                .build();
    }

    @GetMapping("/{id}")
    public Question show(@PathVariable long id) {
        return qnaService.findById(id);
    }

    @PostMapping()
    public ResponseEntity<Object> create(@LoginUser User loginUser, @RequestBody Question question) {
        Question savedQuestion = qnaService.create(loginUser, question);
        return ResponseEntity
                .created(URI.create("/api/questions/" + savedQuestion.getId()))
                .build();
    }

    @GetMapping()
    public Iterable<Question> List() {
        return qnaService.findAll();
    }

}
