package pl.tmaj.hsbc;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Tweet {

    private static final int MAX_LENGTH = 140;

    @Id
    @GeneratedValue
    private Long id;
    private String user;
    private String message;

    public Tweet(String user, String tweet) {
        this.user = user;
        this.message = checkLength(tweet);
    }

    private String checkLength(String tweet) {
        if (tweet.length() > MAX_LENGTH) {
            return "INVALID";
        }
        return tweet;
    }

    public Long getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }
}
