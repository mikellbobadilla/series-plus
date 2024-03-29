package ar.mikellbobadilla.genre.repositories;

import ar.mikellbobadilla.v1.genre.entity.GenreEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(showSql = false)
@Transactional(Transactional.TxType.NOT_SUPPORTED)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
class GenreRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(entityManager);
    }

    @Test
    void verifyPersistGenreEntity() {
        GenreEntity genre = GenreEntity.builder()
                .name("acción")
                .build();
        assertNull(genre.getId());
        entityManager.persist(genre);
        assertNotNull(genre.getId());
    }

    @Test
    void verifyFindGenreEntity() {
        GenreEntity genre = GenreEntity.builder()
                .name("acción")
                .build();
        entityManager.persist(genre);
        GenreEntity genreFromDB = entityManager.find(GenreEntity.class, genre.getId());
        assertNotNull(genreFromDB);
        assertEquals(genre.getName(), genreFromDB.getName());
    }

    @Test
    void verifyDeleteGenreEntity() {
        GenreEntity genre = GenreEntity.builder()
                .name("acción")
                .build();
        entityManager.persist(genre);
        entityManager.remove(genre);
        GenreEntity genreFromDB = entityManager.find(GenreEntity.class, genre.getId());
        assertNull(genreFromDB);
    }

}