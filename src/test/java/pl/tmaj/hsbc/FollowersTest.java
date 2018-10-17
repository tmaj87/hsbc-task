package pl.tmaj.hsbc;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FollowersTest {

    private static final String DUMMY_USER = "dummyUser";
    private static final String OTHER_DUMMY_USER = "otherDummyUser";

    private Followers followers;

    @Before
    public void setUp() {
        followers = new Followers();
    }

    @Test
    public void shouldStoreNewFollower() {
        followDummyUser();

        Set<String> result = followers.getFollowersFor(DUMMY_USER);

        assertTrue(result.contains(OTHER_DUMMY_USER));
    }

    @Test
    public void shouldRemoveUserWhenUnfollowed() {
        followDummyUser();
        followers.unfollow(DUMMY_USER, OTHER_DUMMY_USER);

        Set<String> result = followers.getFollowersFor(DUMMY_USER);

        assertFalse(result.contains(OTHER_DUMMY_USER));
    }

    private void followDummyUser() {
        followers.follow(DUMMY_USER, OTHER_DUMMY_USER);
    }
}