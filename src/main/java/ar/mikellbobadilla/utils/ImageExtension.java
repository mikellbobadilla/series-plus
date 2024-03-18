package ar.mikellbobadilla.utils;

import lombok.Getter;

@Getter
public enum ImageExtension {
    JPG("jpg"),
    PNG("png"),
    JPE("jpe"),
    JPEG("jpeg"),
    WEBP("webp");

    private final String extension;

    ImageExtension(String extension) {
        this.extension = extension;
    }

}
