package ar.mikellbobadilla.v1.genre.repository;

import ar.mikellbobadilla.v1.genre.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

    boolean existsByName(String name);
}
