package pl.tmaj.hsbc;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/addTweet/{tweet}")
    public void addTweet(@Param("tweet") String tweet) {

    }
}
