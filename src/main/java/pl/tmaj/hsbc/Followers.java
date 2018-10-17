package pl.tmaj.hsbc;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import static java.util.Collections.unmodifiableSet;

@Component
public class Followers {

    private final Map<String, Set<String>> followers;

    public Followers() {
        this.followers = new ConcurrentHashMap<>();
    }

    public Set<String> getFollowersFor(String user) {
        return unmodifiableSet(getFollowers(user));
    }

    private Set<String> getFollowers(String user) {
        return followers.getOrDefault(user, new CopyOnWriteArraySet<>());
    }

    public void follow(String user, String following) {
        Set<String> set = getFollowers(user);
        set.add(following);
        followers.put(user, set);
    }

    public void unfollow(String user, String unfollowing) {
        Set<String> set = getFollowers(user);
        set.remove(unfollowing);
        followers.put(user, set);
    }
}
