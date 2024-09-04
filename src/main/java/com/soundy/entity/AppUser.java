package com.soundy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "permium", nullable = false)
    private Boolean permium = false;

    @Column(name = "explicit", nullable = false)
    private Boolean explicit = false;

    @Column(name = "username", nullable = false)
    private String username;

    @ManyToMany
    @JoinTable(name = "user2artist",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id"))
    private Set<Artist> artists = new LinkedHashSet<>();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AppUser{");
        sb.append("id=").append(id);
        sb.append(", permium=").append(permium);
        sb.append(", explicit=").append(explicit);
        sb.append(", username='").append(username).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
