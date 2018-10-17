package pl.tmaj.hsbc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TweetTest {

    private static final int MAX_LENGTH = 140;
    private static final String DUMMY_USER = "dummyUser";
    private static final String INVALID = "INVALID";

    @Test
    public void shouldBeOf140CharactersMax() {
        String maxLengthTweet = newTweetOf(MAX_LENGTH);

        Tweet tweet = new Tweet(DUMMY_USER, maxLengthTweet);

        assertEquals(maxLengthTweet, tweet.getMessage());
    }

    @Test
    public void shouldBeInvalidIfMoreThan140Characters() {
        String maxLengthTweet = newTweetOf(MAX_LENGTH + 1);

        Tweet tweet = new Tweet(DUMMY_USER, maxLengthTweet);

        assertEquals(INVALID, tweet.getMessage());
    }

    private static String newTweetOf(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append("a");
        }
        return stringBuilder.toString();
    }
}
