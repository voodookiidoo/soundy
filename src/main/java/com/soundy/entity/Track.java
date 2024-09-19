package com.soundy.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "track")
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"artists"})
@ToString
public class Track {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", length = Integer.MAX_VALUE)
    private String title;

    @Column(name = "explicit")
    private Boolean explicit = true;

    @Column(name = "premium")
    private Boolean premium = false;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinTable(
            name = "artist2track", // Название промежуточной таблицы
            joinColumns = @JoinColumn(name = "track_id"), // Связь с таблицей track
            inverseJoinColumns = @JoinColumn(name = "artist_id") // Связь с таблицей artist
    )
    @ToString.Exclude
    @Size(min = 1)
    private Set<Artist> artists = new HashSet<>();


}
