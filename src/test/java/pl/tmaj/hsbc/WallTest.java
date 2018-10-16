package pl.tmaj.hsbc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WallTest {

    private static final String USER = "dummyUser";
    private static final String OTHER_USER = "otherDummyUser";
    private static final List<String> MULTIPLE_USERS = asList(USER, OTHER_USER);
    private static final String MESSAGE = "dummyMessage";
    private static final String OTHER_MESSAGE = "otherDummyMessage";
    private static final String YET_ANOTHER_MESSAGE = "yetAnotherDummyMessage";

    @Autowired
    private Wall wall;

    @Before
    public void setUp() {
        wall.deleteAll();
    }

    @Test
    public void shouldStoreNewTweet() {
        Tweet tweet = saveDummyTweet();

        assertTrue(wall.existsById(tweet.getId()));
    }

    @Test
    public void shouldReturnInReversedOrder() {
        saveTweetWithMessage(MESSAGE);
        saveTweetWithMessage(OTHER_MESSAGE);
        saveTweetWithMessage(YET_ANOTHER_MESSAGE);

        List<Tweet> userWall = wall.findAllByUserOrderByIdDesc(USER);

        assertEquals(YET_ANOTHER_MESSAGE, userWall.get(0).getMessage());
        assertEquals(OTHER_MESSAGE, userWall.get(1).getMessage());
        assertEquals(MESSAGE, userWall.get(2).getMessage());
    }

    @Test
    public void shouldReturnTweetsOfOneUser() {
        saveThreeDummyTweets();

        int wallSize = wall.findAllByUserOrderByIdDesc(USER).size();

        assertEquals(3, wallSize);
    }

    @Test
    public void shouldReturnTweetsOfMultipleUsers() {
        saveTweetsForMultipleUsers();

        List<Tweet> tweets = wall.findAllByUserIsInOrderByIdDesc(MULTIPLE_USERS);

        assertEquals(4, tweets.size());
    }

    private Tweet saveDummyTweet() {
        return wall.save(new Tweet(USER, MESSAGE));
    }

    private void saveThreeDummyTweets() {
        saveDummyTweet();
        saveDummyTweet();
        saveDummyTweet();
    }

    private void saveTweetWithMessage(String message) {
        wall.save(new Tweet(USER, message));
    }

    private void saveTweetsForMultipleUsers() {
        wall.save(new Tweet(USER, OTHER_MESSAGE));
        wall.save(new Tweet(USER, YET_ANOTHER_MESSAGE));
        wall.save(new Tweet(OTHER_USER, OTHER_MESSAGE));
        wall.save(new Tweet(OTHER_USER, YET_ANOTHER_MESSAGE));
    }
}
