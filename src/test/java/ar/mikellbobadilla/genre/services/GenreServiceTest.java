package ar.mikellbobadilla.genre.services;

import ar.mikellbobadilla.v1.exceptions.GenreNotFoundException;
import ar.mikellbobadilla.v1.genre.dto.CreateGenreDTO;
import ar.mikellbobadilla.v1.genre.dto.GenrePageResponse;
import ar.mikellbobadilla.v1.genre.dto.GenreResponse;
import ar.mikellbobadilla.v1.genre.entity.GenreEntity;
import ar.mikellbobadilla.v1.genre.repository.GenreRepository;
import ar.mikellbobadilla.v1.genre.service.GenreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class GenreServiceTest {

    @Mock
    private GenreRepository repository;

    @InjectMocks
    private GenreService service;

    @BeforeEach
    void setUp() {
        service = new GenreService(repository);
    }

    @Test
    void testFindAll() {
        int size = 10;
        List<GenreEntity> genreEntities = new ArrayList<>();

        Pageable pageable = PageRequest.of(0, size);
        Page<GenreEntity> genrePage = new PageImpl<>(genreEntities, pageable, 0);
        when(repository.findAll(pageable)).thenReturn(genrePage);

        GenrePageResponse result = service.findAll(1, size);

        assertNotNull(result);

        verify(repository, times(1)).findAll(pageable);

    }

    @Test
    void testPageDoesNotExist() {
        int page = 2;
        int size = 10;
        Pageable pageable = PageRequest.of(page -1, size);
        Page<GenreEntity> entities = new PageImpl<>(new ArrayList<>(), pageable, 0);
        when(repository.findAll(pageable)).thenReturn(entities);

        assertThrows(GenreNotFoundException.class, () -> service.findAll(page, size));
        verify(repository, times(1)).findAll(pageable);
    }

    @Test
    void testCreateGenre() {
        GenreEntity genre = GenreEntity.builder()
                .name("acción")
                .build();

        when(repository.save(genre)).thenReturn(genre);

        GenreResponse response = service.save(new CreateGenreDTO("acción"));

        assertNotNull(response);
        assertEquals("acción", response.name());
    }

    @Test
    void testCreateGenreExists() {
        when(repository.existsByName("acción")).thenReturn(true);

        assertThrows(GenreNotFoundException.class, () -> service.save(new CreateGenreDTO("acción")));
    }

    @Test
    void testGetById() {
        GenreEntity genre = GenreEntity.builder()
                .id(1L)
                .name("acción")
                .build();

        when(repository.findById(1L)).thenReturn(java.util.Optional.of(genre));

        GenreResponse response = service.getById(1L);

        assertNotNull(response);
        assertEquals("acción", response.name());
    }

    @Test
    void testUpdateGenre() {
        GenreEntity genre = GenreEntity.builder()
                .id(1L)
                .name("acción")
                .build();

        when(repository.findById(1L)).thenReturn(java.util.Optional.of(genre));

        service.update(new CreateGenreDTO("comedia"), 1L);

        verify(repository, times(1)).saveAndFlush(genre);
    }

    @Test
    void testDeleteGenre() {
        when(repository.existsById(1L)).thenReturn(true);

        service.delete(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}