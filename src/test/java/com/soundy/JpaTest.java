package com.soundy;

import com.soundy.entity.Account;
import com.soundy.entity.Artist;
import com.soundy.entity.Genre;
import com.soundy.entity.Track;
import com.soundy.repository.AccountRepository;
import com.soundy.repository.AppUserRepository;
import com.soundy.repository.ArtistRepository;
import com.soundy.repository.GenreRepository;
import com.soundy.repository.PlaylistRepository;
import com.soundy.repository.TrackRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
public class JpaTest {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private AccountRepository accountRepository;


    /**
     * Попробуем сохранить артиста
     */
    @Test
    void addSomeArtist() {
        // сохраним аккаунт артиста
        var artistAccount = new Account()
                .setUsername("shupac").setPassword("takur")
                .setAccountRole(Account.Role.ROLE_ARTIST);
        var savedAcc = accountRepository.save(artistAccount);
        var artist = new Artist().setAccount(savedAcc).setDescription("gangsta");
        artistRepository.save(artist);
        artist = artistRepository.findById(savedAcc.getId()).get();
        // убедимся в том что у аккаунта и артиста совпадают айди
        assertEquals(artist.getId(), savedAcc.getId());
        // что артист ссылается на тот же аккаунт
        assertEquals(artist.getAccount().getUsername(), savedAcc.getUsername());
        // что артист не имеет подписчиков
        assertEquals(0, artist.getSubscribers().size());
    }


    public Track randomArtistTrack() {
        var artistAccount = new Account()
                .setUsername("stubname").setPassword("stubpass")
                .setAccountRole(Account.Role.ROLE_ARTIST);
        var savedAcc = accountRepository.save(artistAccount);
        var artist = new Artist().setAccount(savedAcc).setDescription("stubdesc");
        var genre = new Genre().setGenreName("hip-hop").setDescription("hip-hop forever");
        genre = genreRepository.save(genre);
        artist = artistRepository.save(artist);
        var track = new Track().setTitle("stubtrackname").setGenre(genre).setArtists(Set.of(artist));
        track = trackRepository.save(track);
        // убедимся в том что этот тест срабатывает корректно
        // чтобы использовать добавленные сущности в дальнейшем тестировании
//        assertEquals(track.getGenre().getGenreName(), genre.getGenreName());
//        assertEquals(track.getArtists().stream().findFirst().get().getId(), artist.getId());
        return track;
    }

    @Test
    void delTrackForArtist() {
        var track = randomArtistTrack();
        // попробуем удалить трек из бд по его id
        trackRepository.deleteById(track.getId());
        // убедимся в том что трека больше нет в бд
        assertTrue(trackRepository.findById(track.getId()).isEmpty());

    }





}
