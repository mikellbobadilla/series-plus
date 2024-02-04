package ar.mikellbobadilla.repositories;

import ar.mikellbobadilla.entities.SerieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieRepository extends JpaRepository<SerieEntity,Long> {
}
