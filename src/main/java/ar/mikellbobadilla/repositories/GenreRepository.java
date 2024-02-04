package ar.mikellbobadilla.repositories;

import ar.mikellbobadilla.entities.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

    boolean existsByName(String name);
}
