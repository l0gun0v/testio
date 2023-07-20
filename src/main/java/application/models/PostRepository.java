package application.models;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Integer> {
    @Query("SELECT p FROM Post p WHERE p.ownerUsername = :ownerUsername ORDER BY p.id DESC")
    List<Post> findPostsByUsername(@Param("ownerUsername") String ownerUsername);

    @Query("SELECT p FROM Post p ORDER BY p.id DESC")
    List<Post> allPosts();

    @Query("SELECT p FROM Post p WHERE p.ownerUsername IN :contacts ORDER BY p.id DESC")
    List<Post> allContactsPosts(@Param("contacts") List<String> contacts);
}
