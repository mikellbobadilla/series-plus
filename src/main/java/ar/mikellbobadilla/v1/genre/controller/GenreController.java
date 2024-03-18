package ar.mikellbobadilla.v1.genre.controller;

import ar.mikellbobadilla.v1.genre.dto.CreateGenreDTO;
import ar.mikellbobadilla.v1.genre.dto.GenrePageResponse;
import ar.mikellbobadilla.v1.genre.dto.GenreResponse;
import ar.mikellbobadilla.v1.genre.service.GenreService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genres")
@AllArgsConstructor
public class GenreController {

    private final GenreService services;

    @GetMapping
    public ResponseEntity<GenrePageResponse> findAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return new ResponseEntity<>(services.findAll(page, size), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GenreResponse> save(@RequestBody @Valid CreateGenreDTO dto) {
        return new ResponseEntity<>(services.save(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreResponse> getById(@PathVariable Long id) {
        return new ResponseEntity<>(services.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Valid CreateGenreDTO dto, @PathVariable Long id) {
        services.update(dto, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        services.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
