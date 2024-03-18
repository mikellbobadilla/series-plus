package ar.mikellbobadilla.v1.genre.service;

import ar.mikellbobadilla.v1.exceptions.GenreNotFoundException;
import ar.mikellbobadilla.v1.genre.dto.CreateGenreDTO;
import ar.mikellbobadilla.v1.genre.dto.GenrePageResponse;
import ar.mikellbobadilla.v1.genre.dto.GenreResponse;
import ar.mikellbobadilla.v1.genre.entity.GenreEntity;
import ar.mikellbobadilla.v1.genre.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GenreService {

    private final GenreRepository repository;

    public GenrePageResponse findAll(int page, int size) {
        Pageable pageable =  PageRequest.of(--page, size);
        Page<GenreEntity> genres = repository.findAll(pageable);

        if (genres.isEmpty() && page > 0) {
            throw new GenreNotFoundException("Page not found");
        }

        return pageResponseMapper(genres);
    }

    public GenreResponse save(CreateGenreDTO dto) {

        if (repository.existsByName(dto.name())) {
            throw new GenreNotFoundException("Genre exists");
        }

        GenreEntity genre = GenreEntity.builder()
        .name(dto.name())
        .build();
        return genreResponseMapper(repository.save(genre));
    }

    public GenreResponse getById(Long id) {
        return repository
                .findById(id)
                .map(this::genreResponseMapper)
                .orElseThrow(() -> new GenreNotFoundException("Genre not found"));
    }

    public void update(CreateGenreDTO dto, Long id) {

        GenreEntity genre = repository
                .findById(id)
                .orElseThrow(() -> new GenreNotFoundException("Genre not found"));
        genre.setName(dto.name());
        repository.saveAndFlush(genre);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new GenreNotFoundException("Genre not found");
        }
        repository.deleteById(id);
    }

    private GenrePageResponse pageResponseMapper(Page<GenreEntity> genres) {
        return new GenrePageResponse(
                genres.getContent().stream().map(this::genreResponseMapper).toList(),
                genres.getNumber() + 1,
                genres.getSize(),
                genres.getTotalPages(),
                genres.getTotalElements()
        );
    }

    private GenreResponse genreResponseMapper(GenreEntity genre) {
        return new GenreResponse(genre.getId(), genre.getName());
    }
}
