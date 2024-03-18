package ar.mikellbobadilla.v1.serie.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "seasons")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SeasonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 50)
    private String title;
    private String path;
    @Column(length = 50)
    private String language;
    @ManyToOne
    private SerieEntity content;
}
