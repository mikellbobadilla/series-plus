package ar.mikellbobadilla.v1.serie.entity;

import ar.mikellbobadilla.v1.genre.entity.GenreEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "series")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SerieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 50, nullable = false, unique = true)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String poster;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private Integer seasons;
    private Boolean status = false;
    @ManyToMany(mappedBy =  "contents", targetEntity = GenreEntity.class)
    private Set<GenreEntity> genres = new HashSet<>();
}
