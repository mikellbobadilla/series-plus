package ar.mikellbobadilla.repositories.genre;

import ar.mikellbobadilla.entities.genre.GenreModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<GenreModel, Long> {

    boolean existsByName(String name);
}
