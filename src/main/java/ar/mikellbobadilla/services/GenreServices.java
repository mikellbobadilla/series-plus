package ar.mikellbobadilla.services;

import ar.mikellbobadilla.advice.exceptions.GenreNotFoundException;
import ar.mikellbobadilla.dto.GenrePageResponse;
import ar.mikellbobadilla.dto.GenreResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ar.mikellbobadilla.dto.CreateGenre;
import ar.mikellbobadilla.entities.GenreEntity;
import ar.mikellbobadilla.repositories.GenreRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GenreServices {

    private final GenreRepository repository;

    public GenrePageResponse findAll(int page, int size) {
        Pageable pageable =  PageRequest.of(--page, size);
        Page<GenreEntity> genres = repository.findAll(pageable);

        if (genres.isEmpty() && page > 0) {
            throw new GenreNotFoundException("Page not found");
        }

        return pageResponseMapper(genres);
    }

    public GenreResponse save(CreateGenre dto) throws Exception {

        if (dto == null) {
            throw new Exception("Name is required");
        }

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

    public void update(CreateGenre dto, Long id) {

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
