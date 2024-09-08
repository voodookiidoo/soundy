package com.soundy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Formula;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "artist")
@Table
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"tracks"})
@Accessors(chain = true)
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @ManyToMany(mappedBy = "artists")
    private Set<Track> tracks = new LinkedHashSet<>();


    @Formula("(select count(user2artist.user_id) from user2artist where user2artist.artist_id = id)")
    private Integer subAmount;


//    @ManyToMany()
//    @JoinTable(name = "user2artist",
//            joinColumns = {@JoinColumn(name = "artist_id")},
//            inverseJoinColumns = {@JoinColumn(name = "user_id")})
//    private Set<AppUser> appUsers = new LinkedHashSet<>();


}
