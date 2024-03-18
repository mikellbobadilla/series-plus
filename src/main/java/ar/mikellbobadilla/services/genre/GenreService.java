package ar.mikellbobadilla.services.genre;

import ar.mikellbobadilla.dtos.genre.GenreRequestDTO;
import ar.mikellbobadilla.dtos.genre.GenreResponseDTO;
import ar.mikellbobadilla.dtos.genre.PageGenreDTO;
import ar.mikellbobadilla.entities.genre.GenreModel;
import ar.mikellbobadilla.exceptions.genre.GenreException;
import ar.mikellbobadilla.exceptions.genre.GenreNotFoundException;
import ar.mikellbobadilla.repositories.genre.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GenreService {

    private final GenreRepository repository;

    public PageGenreDTO getAll(int page, int size) {
        Pageable pageable = PageRequest.of(--page, size);
        Page<GenreModel> genres = repository.findAll(pageable);

        if (page < 0) {
            throw new GenreException("Page cannot be less than 0");
        }

        if (genres.getTotalPages() > page) {
            throw new GenreNotFoundException("Page not found");
        }

        return pageMapper(genres);
    }

    public GenreResponseDTO save(GenreRequestDTO dto) {
        if (repository.existsByName(dto.name())){
            throw new GenreException("Genre Exists");
        }

        GenreModel model = GenreModel.builder()
                .name(dto.name())
                .build();
        return genreMapper(repository.saveAndFlush(model));
    }

    public GenreResponseDTO getById(Long id) {
        return repository.findById(id)
                .map(this::genreMapper)
                .orElseThrow(() -> new GenreNotFoundException("Genre not found"));
    }

    public void update(Long id, GenreRequestDTO dto) {
        GenreModel model = repository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException("Genre not found"));
        model.setName(dto.name());
        repository.saveAndFlush(model);
    }

    public void delete(Long id) {
        if (repository.existsById(id))
            throw new GenreNotFoundException("Genre not found");
        repository.deleteById(id);
    }

    /* Mappers */
    private PageGenreDTO pageMapper(Page<GenreModel> genres) {
        return PageGenreDTO.builder()
                .content(genres.getContent().stream().map(this::genreMapper).toList())
                .page(genres.getNumber() + 1)
                .size(genres.getSize())
                .totalPages(genres.getTotalPages())
                .totalElements(genres.getTotalElements())
                .build();
    }

    private GenreResponseDTO genreMapper(GenreModel model) {
        return new GenreResponseDTO(model.getId(), model.getName());
    }
}
