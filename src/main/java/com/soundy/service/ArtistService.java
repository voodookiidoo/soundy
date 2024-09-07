package com.soundy.service;

import com.soundy.dto.artist.AddArtistReq;
import com.soundy.dto.artist.GetArtistReq;
import com.soundy.entity.Artist;
import com.soundy.mapper.SoundyMapper;
import com.soundy.repository.ArtistRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.Pair;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
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

//    TrackService trackService;

    public void createArtist(AddArtistReq req) {
        Artist artist = SoundyMapper.INSTANCE.toArtist(req);

        artistRepository.save(artist);
    }

    public List<Artist> findArtistsById(Set<Integer> id) {
        return artistRepository.findAllById(id);
    }

    public Optional<Artist> findArtistById(GetArtistReq req) {
        return artistRepository.findById(req.getId());
    }

    public boolean loadDbFromFile() {
        Resource r = loader.getResource("classpath:top50.csv");
        try (FileReader reader = new FileReader(r.getFile());
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            Map<String, List<String>> res = csvParser.stream().map(strings -> new Pair<>(strings.get("Track.Name"), strings.get("Artist.Name")))
                    .collect(Collectors.groupingBy(
                            pair -> pair.b,
                            Collectors.mapping(pair -> pair.a, Collectors.toList())
                    ));
            return true;
        } catch (IOException e) {
            log.error("something happened!", e);
            return false;
        }
    }

}
