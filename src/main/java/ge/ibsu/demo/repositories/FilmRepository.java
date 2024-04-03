package ge.ibsu.demo.repositories;

import ge.ibsu.demo.entities.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface FilmRepository extends JpaRepository<Film, Long>{

    @Query("SELECT f FROM Film f WHERE " +
            "(:q IS NULL OR f.title LIKE %:q% OR f.description LIKE %:q%) " +
            "AND (:year IS NULL OR f.releaseYear = :year) " +
            "AND (:language IS NULL OR f.language = :language)")
    Page<Film> search(
            @Param("q") String q,
            @Param("year") Integer year,
            @Param("language") String language,
            Pageable pager
    );

}
