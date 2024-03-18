package ar.mikellbobadilla.v1.genre.entity;


import ar.mikellbobadilla.v1.serie.entity.SerieEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "genres")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 10, nullable = false, unique = true)
    private String name;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<SerieEntity> contents = new HashSet<>();
}
