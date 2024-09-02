package com.soundy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Entity(name = "track")
@Getter
@Setter
@Accessors(chain = true)
public class TrackEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "explicit")
    private Boolean explicit;

    @Column(name = "premium")
    private Boolean premium;


    // Трек может иметь много артистов, а артист - много треков
    @ManyToMany
    @JoinTable(
            name = "track_artist",
            joinColumns = @JoinColumn(name = "track_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private List<ArtistEntity> artists;

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private PlaylistEntity playlist;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUserEntity user;


}
