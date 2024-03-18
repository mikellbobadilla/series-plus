package ar.mikellbobadilla.v1.serie.repository;

import ar.mikellbobadilla.v1.serie.entity.SeasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonRepository extends JpaRepository<SeasonEntity,Long> {
}
