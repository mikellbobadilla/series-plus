package ar.mikellbobadilla.repositories.serie;

import ar.mikellbobadilla.entities.serie.SerieModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieRepository extends JpaRepository<SerieModel,Long> {
}
