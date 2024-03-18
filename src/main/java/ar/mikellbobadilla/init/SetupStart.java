package ar.mikellbobadilla.init;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class SetupStart implements CommandLineRunner {

    @Value("${app.storage.location}")
    private String storageDirectory;

    @Override
    public void run(String... args) throws Exception {
        if (storageDirectory == null) {
            throw new RuntimeException("Can't detect storage location, please set app.storage.location in application.properties");
        }

        Path storage = Paths.get(storageDirectory);
        if (Files.notExists(storage.toAbsolutePath())) {
            Files.createDirectory(storage);
        }
    }
}
