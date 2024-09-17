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
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "playlist")

@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @Size(max = 255)
    @Column(name = "title")
    private String title;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String desc;

    @ManyToOne
    @JoinTable(name = "playlist2owner",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "owner_id"))
    @EqualsAndHashCode.Exclude
    private AppUser owner;


    @ManyToMany
    @JoinTable(name = "playlist2track",
            inverseJoinColumns = @JoinColumn(name = "track_id"),
            joinColumns = @JoinColumn(name = "playlist_id"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Track> tracks = new HashSet<>();


//    @Formula("(select count(public.playlist2user.user_id) from playlist2user where playlist2user.playlist_id.id)")
//    private Integer listeners = 0;

}
