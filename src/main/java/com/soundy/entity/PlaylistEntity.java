package com.soundy.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity(name = "playlist")
//@SecondaryTables({
//		@SecondaryTable(name = "titans"),
//		@SecondaryTable(name = "pilot_weapon")
//})
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class PlaylistEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
