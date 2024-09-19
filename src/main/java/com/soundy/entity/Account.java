package com.soundy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "account")
@Getter
@Setter
@ToString
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Account implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false, name = "account_role")
    private Role accountRole;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(accountRole);
    }

    public enum Role implements GrantedAuthority {
        ROLE_ADMIN,
        ROLE_USER,
        ROLE_ARTIST;

        @Override
        public String getAuthority() {
            return name();
        }
    }

}
