package com.soundy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Entity(name = "artist")
//@SecondaryTables({
//        @SecondaryTable(name = "titans"),
//        @SecondaryTable(name = "pilot_weapon")
//})
@Getter
@Setter
@Accessors(chain = true)
public class ArtistEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name = "description")
    private String desc;

    @ManyToMany(mappedBy = "artists")
    private List<AppUserEntity> subs;

}
