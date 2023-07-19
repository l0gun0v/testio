package application.models;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Integer> {
    @Query("SELECT p FROM Post p WHERE p.ownerID = :ownerID")
    List<Post> findPostsByUser(@Param("ownerID") int ownerID);

    @Query("SELECT p FROM Post p ORDER BY p.id DESC")
    List<Post> allPosts();
}
