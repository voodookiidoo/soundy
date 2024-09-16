package com.soundy;

import com.soundy.entity.Artist;
import com.soundy.entity.Playlist;
import com.soundy.entity.Track;
import com.soundy.repository.AppUserRepository;
import com.soundy.repository.ArtistRepository;
import com.soundy.repository.PlaylistRepository;
import com.soundy.repository.TrackRepository;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
public class JpaTest {

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private ArtistRepository artistRepository;


    /**
     * Убедимся в том что все бины на своих местах
     */
    @Test
    void init() {
        Assertions.assertNotNull(trackRepository);
    }


    /**
     * Попробуем сохранить артиста
     */
    @Test
    void addSomeArtist() {
        var name = "Tupac";
        var desc = "gangsta";
        var artist = new Artist().setName(name).setDescription(desc);
        artist = artistRepository.save(artist);
        log.info("Saved artist {}", artist);
    }


    /**
     * Проверка корректного удаления трека
     */
    @Test
    void trackDel() {
        var before = trackRepository.findAll();
        // удалим первый попавшийся в базе трек, и получим количество сущностей подверженных изменению (affected)
        var x = trackRepository.removeById(before.get(0).getId());
        // запросим список треков снова
        var after = trackRepository.findAll();
        // количество треков должно уменьшиться на единицу
        Assertions.assertEquals(before.size() - 1, after.size());
        // количество затронутых сущностей должно равняться единице
        Assertions.assertEquals(1, x);
    }

    /**
     * Проверка создания плейлиста
     */
    @Test
    void playListCreate() {
        var opt = appUserRepository.findAll(Pageable.ofSize(1)).stream().findFirst();
        if (opt.isEmpty()) return;
        var owner = opt.get();
        // берём случайного юзера, который будет владельцем плейлиста
        // возьмём n случайных треков и сохраним их
        var n = 3;
        var tracks = trackRepository.findAll(Pageable.ofSize(n)).stream().collect(Collectors.toSet());
        // создадим плейлист, в который положим полученные треки, назначим ему владельца
        var playlist = new Playlist().setTracks(tracks).setTitle("Heavy metal").setDesc("roooooock").setOwner(owner);
        // сохраняем плейлист и проверяем корректность его сохранения
        playlist = playlistRepository.save(playlist);
        // проверяем что владельцем плейлиста является созданный ранее юзер
        Assertions.assertEquals(playlist.getOwner().getId(), owner.getId());
        // убедимся в том что количество треков в плейлисте совпадает с заданным
        Assertions.assertEquals(playlist.getTracks().size(), n);

        // вытащим данные по обновлённому владельцу из бд
        owner = appUserRepository.findById(owner.getId()).get();
        // убедимся в том что среди его плейлистов появился новый
        Assertions.assertTrue(owner.getPlaylists().contains(playlist));

    }


    /**
     * Тест удаления плейлиста из базы
     */
    @Test
    void playlistDelete() {

        var opt = playlistRepository.findAll().stream().findFirst();
        if (opt.isEmpty()) return;
        // получим плейлист
        var playlist = opt.get();
        // извлечём из него треки
        var tracks = playlist.getTracks();
        // попробуем удалить плейлист, и проверим не дропнулись ли треки привязанные к нему
        playlistRepository.deleteById(playlist.getId());

        // получаем из бд треки, которые раньше были в плейлисте
        var dbTracks = trackRepository.findAllById(tracks.stream().map(Track::getId).collect(Collectors.toSet()));

        // убедимся в том что треки не выпали из базы вместе с плейлистом
        Assertions.assertEquals(tracks.size(), dbTracks.size());

    }

    /**
     * Тест удаления артиста с привязанными к нему треками
     */
    @Test
    void deleteArtist() {
        var opt = artistRepository.findByTracksNotEmpty().stream().findFirst();
        if (opt.isEmpty()) return;
        // вытащим из базы случайного артиста с треками
        var artist = opt.get();
        // получим его треки
        var tracks = artist.getTracks();
        // удалим артиста из базы
        artistRepository.deleteById(artist.getId());
        // получим список треков из базы по старым id
        var tracksAfter = trackRepository.findAllById(tracks.stream().map(Track::getId).collect(Collectors.toSet()));
        log.info("Tracks before delete {}", tracks);
        log.info("Tracks after delete {}", tracksAfter);
        // убедимся в том что треков в базе больше нет
        Assertions.assertEquals(0, tracksAfter.size());

    }

    @Test
    void saveTrack() {
        var opt = artistRepository.findAll(Pageable.ofSize(1)).stream().findFirst();
        if (opt.isEmpty()) return;
        Artist artist = opt.get();
        // берём случайного аритста

        // посмотрим его треки
        HashSet<Track> tracksBefore = new HashSet<>(artist.getTracks());
        // попробуем создать трек, назначить ему этого артиста, а затем сохранить
        String name = "wu tang forever";
        Track track = new Track().setTitle(name);
        track.setArtists(Set.of(artist));
        artist.getTracks().add(track);
        // artist.getTracks().add(track);
        var artistAfter = artistRepository.findById(artist.getId()).orElse(null);
        if (artistAfter == null) return;
        // убедимся в том что мы имеем дело с тем же артистом
        Assertions.assertEquals(artistAfter.getId(), artist.getId());
        // проверим, что после добавления трека количество треков у артиста увеличилось на единицу
        log.info("Artist before {}, artist after{}", artist, artistAfter);
        log.info("Artist has tracks {}", artistAfter.getTracks());
        Track finalTrack = track;
        Assertions.assertEquals(tracksBefore.size() + 1, artistAfter.getTracks().size());
        // найдём этот трек
        track = artistAfter.getTracks().stream().filter(t -> t.getId() == finalTrack.getId()).findFirst().get();
        // проверим его имя
        Assertions.assertEquals(name, track.getTitle());
        // убедимся в том что незаполненные поля получили свои дефолтные значения
        // по умолчанию любой трек является explicit контентом
        Assertions.assertTrue(track.getExplicit());
        // и не требует премиум доступа для прослушиввания
        Assertions.assertFalse(track.getPremium());

    }

    @Test
    void saveBadTrack() {
        // попробуем сохранить трек без артиста
        var track = new Track().setTitle("eye of the tiger");
        // убедимся в том что валидатор забракует такую операцию и выдаст исключение
        Assertions.assertThrows(ConstraintViolationException.class, () -> trackRepository.save(track));
    }

}
