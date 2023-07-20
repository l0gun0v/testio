package application.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findUserByUsername(@Param("username") String username);
    @Query("SELECT u FROM User u ORDER BY u.username")
    List<User> getAllUsers();
    @Query("SELECT u FROM User u WHERE u.id not in (SELECT r.userTwo FROM Relationship r WHERE r.userOne =:ID) ORDER BY u.username")
    List<User> getAllNotContacts(@Param("ID") Long ID);

    @Query("SELECT u FROM User u WHERE u.id in (SELECT r.userTwo FROM Relationship r WHERE r.userOne =:ID) ORDER BY u.username")
    List<User> getAllContacts(@Param("ID") Long ID);

}