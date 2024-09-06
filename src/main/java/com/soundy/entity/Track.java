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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "track")
@Table
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"artists"})
public class Track {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", length = Integer.MAX_VALUE)
    private String title;

    @Column(name = "explicit")
    private Boolean explicit;

    @Column(name = "premium")
    private Boolean premium;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "artist2track", // Название промежуточной таблицы
            joinColumns = @JoinColumn(name = "track_id"), // Связь с таблицей track
            inverseJoinColumns = @JoinColumn(name = "artist_id") // Связь с таблицей artist
    )
    private Set<Artist> artists = new HashSet<>();

//    @ManyToMany()
//    @JoinTable(name = "playlist2track",
//            joinColumns = @JoinColumn(name = "track_id"),
//            inverseJoinColumns = @JoinColumn(name = "playlist_id"))
//    private Set<Playlist> playlists = new LinkedHashSet<>();

}
