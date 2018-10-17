package pl.tmaj.hsbc;

import org.junit.Test;
import org.springframework.http.HttpHeaders;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ControllerTest {

    private static final String DUMMY_USER = "dummyUser";
    private static final String DUMMY_MESSAGE = "dummyMessage";

    private final Wall wall = mock(Wall.class);
    private final Followers followers = mock(Followers.class);
    private final HttpHeaders headers = mock(HttpHeaders.class);

    private Controller controller = new Controller(wall, followers);

    @Test
    public void shouldBeAbleToGetEverything() {
        controller.getEverything();

        verify(wall).findAllByOrderByIdDesc();
    }

    @Test
    public void shouldBeAbleToTweet() {
        controller.tweet(headers, DUMMY_MESSAGE);

        verify(wall).save(any(Tweet.class));
    }

    @Test
    public void shouldBeAbleToSeeOnlyMineTweets() {
        controller.mine(headers);

        verify(wall).findAllByUserOrderByIdDesc(anyString());
    }

    @Test
    public void shouldBeAbleToShowOnlyFollowingTweets() {
        controller.following(headers);

        verify(wall).findAllByUserIsInOrderByIdDesc(anyList());
    }

    @Test
    public void shouldBeAbleToFollow() {
        controller.follow(headers, DUMMY_USER);

        verify(followers).follow(anyString(), eq(DUMMY_USER));
    }

    @Test
    public void shouldBeAbleToUnfollow() {
        controller.unfollow(headers, DUMMY_USER);

        verify(followers).unfollow(anyString(), eq(DUMMY_USER));
    }
}
