package pl.tmaj.hsbc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Wall extends JpaRepository<Tweet, Long> {

    List<Tweet> findAllByUserOrderByIdDesc(@Param("user") String user);

    List<Tweet> findAllByUserIsInOrderByIdDesc(@Param("users") List<String> users);

    List<Tweet> findAllByOrderByIdDesc();
}
