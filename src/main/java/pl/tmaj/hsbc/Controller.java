package pl.tmaj.hsbc;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static pl.tmaj.hsbc.Utils.calculateUserId;

@RestController
public class Controller {

    private Wall wall;
    private Followers followers;

    public Controller(Wall wall, Followers followers) {
        this.wall = wall;
        this.followers = followers;
    }

    @GetMapping("/")
    @ResponseBody
    public List<Tweet> getEverything() {
        return wall.findAllByOrderByIdDesc();
    }

    @GetMapping("/tweet/{message}")
    public void tweet(@RequestHeader HttpHeaders headers, @PathVariable String message) {
        String user = calculateUserId(headers);
        Tweet tweet = new Tweet(user, message);
        wall.save(tweet);
    }

    @GetMapping("/mine")
    @ResponseBody
    public List<Tweet> mine(@RequestHeader HttpHeaders headers) {
        String user = calculateUserId(headers);
        return wall.findAllByUserOrderByIdDesc(user);
    }

    @GetMapping("/following")
    @ResponseBody
    public List<Tweet> following(@RequestHeader HttpHeaders headers) {
        String user = calculateUserId(headers);
        List<String> users = new ArrayList<>(followers.getFollowersFor(user));
        return wall.findAllByUserIsInOrderByIdDesc(users);
    }

    @GetMapping("/follow/{following}")
    public void follow(@RequestHeader HttpHeaders headers, @PathVariable String following) {
        String user = calculateUserId(headers);
        followers.follow(user, following);
    }

    @GetMapping("/unfollow/{unfollowing}")
    public void unfollow(@RequestHeader HttpHeaders headers, @PathVariable String unfollowing) {
        String user = calculateUserId(headers);
        followers.unfollow(user, unfollowing);
    }
}
