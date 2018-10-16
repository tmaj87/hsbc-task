package pl.tmaj.hsbc;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class User {

    private final String name;
    private Set<String> following;

    public User(String name) {
        this.name = name;
        this.following = new CopyOnWriteArraySet<>();
    }

    public void follow(String user) {
        following.add(user);
    }

    public void unfollow(User user) {
        following.remove(user.getName());
    }

    public String getName() {
        return name;
    }
}
