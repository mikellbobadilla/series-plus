package ar.mikellbobadilla.entities;


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
    private Set<ContentEntity> contents = new HashSet<>();
}
