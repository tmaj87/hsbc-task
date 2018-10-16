package pl.tmaj.hsbc;

import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

@Component
public class ExampleDataFiller {

    public ExampleDataFiller(Wall repository) {
        Set<Tweet> tweets = Stream.of("Hello world", "Hello HSBC", "World is so Hell-o", "Tweet, tweet, tweet")
                .map(message -> new Tweet("ExampleUser", message))
                .collect(toSet());
        repository.saveAll(tweets);
    }
}
