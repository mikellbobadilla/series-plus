package ar.mikellbobadilla.v1.serie.repository;

import ar.mikellbobadilla.v1.serie.entity.SerieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieRepository extends JpaRepository<SerieEntity, Long> {

    boolean existsByTitle(String title);
}
