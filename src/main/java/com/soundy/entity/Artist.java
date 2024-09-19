package com.soundy.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@Table(name = "artist")
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"tracks"})
@ToString
public class Artist {

    @Id
    private Integer id;

    @OneToOne()
    @JoinTable(
            name = "account",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private Account account;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @ManyToMany(mappedBy = "artists", cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @ToString.Exclude
    private Set<Track> tracks = new HashSet<>();



}
