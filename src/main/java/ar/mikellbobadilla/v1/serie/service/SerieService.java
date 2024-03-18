package ar.mikellbobadilla.v1.serie.service;

import ar.mikellbobadilla.v1.serie.dto.CreateSerieDTO;
import ar.mikellbobadilla.v1.serie.dto.SerieResponseDTO;
import ar.mikellbobadilla.v1.serie.entity.SerieEntity;
import ar.mikellbobadilla.v1.serie.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class SerieService {

    private final SerieRepository repository;
    public SerieService(SerieRepository serieRepository) {
        repository = serieRepository;
    }

    @Value("${app.storage.location}")
    private String STORAGE_LOCATION;

    @Value("${app.base-url}")
    private String BASE_URL;

    public Page<SerieEntity> findAll(int page, int size) {
        return repository.findAll(PageRequest.of(--page, size));
    }

    public SerieResponseDTO save(CreateSerieDTO dto) {
        if (repository.existsByTitle(dto.title())){
            throw new RuntimeException("Series already exists");
        }

        /* Todo: refactor */
        Path rootStorage = Paths.get(STORAGE_LOCATION, "series");
        Path posterStorage = Paths.get(parseText(dto.title()), "poster");
        Path fullPath = rootStorage.resolve(posterStorage);

        Path fileToCopy = fullPath.resolve(parseText(Objects.requireNonNull(dto.poster().getOriginalFilename())));

        if (!exists(fullPath.toAbsolutePath().toString())){
            boolean isCreated = createDirs(fullPath.toAbsolutePath().toString());
            if (!isCreated) throw new RuntimeException("Can't create directory: " + fullPath);
        }

        try {
            copyFile(dto.poster().getInputStream(), fileToCopy);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String urlParsed = parseToUrl(BASE_URL.concat(parseText(posterStorage.resolve(dto.poster().getOriginalFilename()).normalize().toString())));
        SerieEntity serie = SerieEntity.builder()
                .title(dto.title())
                .description(dto.description())
                .poster(urlParsed)
                .seasons(1)
                .status(true)
                .build();
        return serieResponseMapper(repository.saveAndFlush(serie));
    }

    /* Private Methods */
    private SerieResponseDTO serieResponseMapper(SerieEntity entity) {
        return new SerieResponseDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getPoster(),
                entity.getSeasons(),
                entity.getStatus()
        );
    }

    private String parseToUrl(String url) {
        return url.replace("\\", "/");
    }

    private boolean exists(String path) {
        File file = new File(path);
        return file.exists();
    }

    private boolean createDirs(String path) {
        File file = new File(path);
        return file.mkdirs();
    }

    private boolean createDir(String path) {
        File file = new File(path);
        return file.mkdir();
    }

    private void copyFile(InputStream in, Path target) {
        try {
            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String parseText(String text) {
        return text.trim().toLowerCase().replace(" ", "-");
    }

}
