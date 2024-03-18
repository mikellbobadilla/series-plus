package ar.mikellbobadilla.v1.serie.controller;

import ar.mikellbobadilla.v1.serie.dto.CreateSerieDTO;
import ar.mikellbobadilla.v1.serie.dto.SerieResponseDTO;
import ar.mikellbobadilla.v1.serie.entity.SerieEntity;
import ar.mikellbobadilla.v1.serie.service.SerieService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/series")
@AllArgsConstructor
public class SerieController {

    private final SerieService service;

    @GetMapping
    public ResponseEntity<Page<SerieEntity>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return new ResponseEntity<>(service.findAll(page, size), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SerieResponseDTO> createContent(@ModelAttribute @Valid CreateSerieDTO createSerieDTO) throws IOException {
        SerieResponseDTO serieEntity = service.save(createSerieDTO);
        return new ResponseEntity<>(serieEntity, HttpStatus.CREATED);
    }


}
