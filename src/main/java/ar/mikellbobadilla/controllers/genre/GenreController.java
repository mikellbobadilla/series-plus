package ar.mikellbobadilla.controllers.genre;

import ar.mikellbobadilla.dtos.genre.GenreRequestDTO;
import ar.mikellbobadilla.dtos.genre.GenreResponseDTO;
import ar.mikellbobadilla.dtos.genre.PageGenreDTO;
import ar.mikellbobadilla.services.genre.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genres")
@AllArgsConstructor
public class GenreController {

    private final GenreService service;

    @GetMapping
    public ResponseEntity<PageGenreDTO> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return new ResponseEntity<>(service.getAll(page, size), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GenreResponseDTO> save(@RequestBody GenreRequestDTO dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreResponseDTO> getBy(@PathVariable Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBy(@PathVariable Long id, GenreRequestDTO dto) {
        service.update(id, dto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
