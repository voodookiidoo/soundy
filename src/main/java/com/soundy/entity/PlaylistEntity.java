package com.soundy.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.SecondaryTables;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Entity(name = "playlist")
@SecondaryTables({
        @SecondaryTable(name = "app_user"),
        @SecondaryTable(name = "track")
})
@Getter
@Setter
@Accessors(chain = true)
public class PlaylistEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description")
    private String desc;

    @ManyToOne()
    @JoinColumn(name = "app_user_id", nullable = false, updatable = false)
    private AppUserEntity owner; // Один плейлист имеет одного владельца

    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrackEntity> tracks;


}
