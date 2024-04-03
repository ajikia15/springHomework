package ge.ibsu.demo.repositories;
import ge.ibsu.demo.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ActorRepository  extends JpaRepository<Actor, Long>{
    @Query("SELECT a.firstName, a.lastName FROM Actor a")
    List<Object[]> findAllActorsFirstAndLastName();
}
