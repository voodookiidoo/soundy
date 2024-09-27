package com.soundy.service;

import com.soundy.config.JwtService;
import com.soundy.entity.Account;
import com.soundy.entity.Admin;
import com.soundy.entity.AppUser;
import com.soundy.entity.Artist;
import com.soundy.repository.AccountRepository;
import com.soundy.repository.AdminRepository;
import com.soundy.repository.AppUserRepository;
import com.soundy.repository.ArtistRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class AuthService implements UserDetailsService {


    JwtService jwtService;

    AccountRepository accountRepository;

    ArtistRepository artistRepository;

    AppUserRepository appUserRepository;

    AdminRepository adminRepository;



    public Account findAndCheckUser(String username, String pwd) throws CredentialException {
        var acc = accountRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        log.info("PASSWORD1 {}, PASSWORD2 {}", acc.getPassword(), pwd);
        if (Objects.equals(acc.getPassword(), pwd)) {
            return acc;
        } else {
            throw new CredentialException("Password is invalid for user!");
        }

    }

    @Transactional
    public Account createUser(String username, String pwd, Account.Role role) {
        Account account = new Account().setUsername(username).setPassword(pwd).setAccountRole(role);
        var savedAccount = accountRepository.save(account);
        accountRepository.flush();
        Integer savedId = savedAccount.getId();
        log.info("SAVED ID IS {}", savedId);
        switch (role) {
            case ROLE_ADMIN -> {
                var admin = new Admin().setId(savedId).setAccount(savedAccount);
                adminRepository.save(admin);
            }
            case ROLE_USER -> {
                var user = new AppUser().setId(savedId).setAccount(savedAccount);
                appUserRepository.save(user);
            }
            case ROLE_ARTIST -> {
                var artist = new Artist().setId(savedId).setAccount(savedAccount);
                artistRepository.save(artist);
            }
        }
        return savedAccount;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username \"%s\" is not in database".formatted(username)));

    }


    public void delUserByName(String username) {
        var acc = accountRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username \"%s\" is not in database".formatted(username)));


    }

}
