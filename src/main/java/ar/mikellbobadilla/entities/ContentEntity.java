package ar.mikellbobadilla.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contents")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ContentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String poster;
    private String description;
    private Integer season;
    @ManyToMany(mappedBy =  "contents", targetEntity = GenreEntity.class)
    private Set<GenreEntity> genres = new HashSet<>();
}
