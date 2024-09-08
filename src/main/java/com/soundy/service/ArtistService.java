package com.soundy.service;

import com.soundy.dto.artist.AddArtistReq;
import com.soundy.dto.artist.GetArtistReq;
import com.soundy.entity.Artist;
import com.soundy.entity.Track;
import com.soundy.mapper.SoundyMapper;
import com.soundy.repository.ArtistRepository;
import com.soundy.repository.TrackRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ArtistService {

    ArtistRepository artistRepository;

    DefaultResourceLoader loader;

    TrackRepository trackService;

    public Artist createArtist(AddArtistReq req) {
        Artist artist = SoundyMapper.INSTANCE.toArtist(req);

        return artistRepository.save(artist);
    }

    public List<Artist> findArtistsById(Set<Integer> id) {
        return artistRepository.findAllById(id);
    }

    public Optional<Artist> findArtistById(GetArtistReq req) {
        return artistRepository.findById(req.getId());
    }

    public boolean fillDbFromFile() {
        Resource r = loader.getResource("classpath:top50.csv");
        try (FileReader reader = new FileReader(r.getFile());
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            Map<String, List<String>> res = csvParser.stream().collect(Collectors.groupingBy(
                    s -> s.get("Artist.Name"),
                    Collectors.mapping(s -> s.get("Track.Name"), Collectors.toList())
            ));
            res.forEach((key, value) -> {
                Artist artist = new Artist().setDescription("").setName(key);
                artist = artistRepository.save(artist);
                Artist finalArtist = artist;
                List<Track> tracks = value.stream().map(song -> new Track().setTitle(song).setArtists(Set.of(finalArtist))).toList();
                trackService.saveAll(tracks);
            });
            return true;
        } catch (IOException e) {
            log.error("something happened!", e);
            return false;
        }
    }

}
