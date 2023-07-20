package application.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RelationshipRepository extends CrudRepository<Relationship, Integer> {

    @Query("SELECT u FROM Relationship u WHERE u.userOne = :userID1 AND u.userTwo = :userID2")
    Relationship findRelationshipByIDs(@Param("userID1") Long userID1, @Param("userID2") Long userID2);

}
