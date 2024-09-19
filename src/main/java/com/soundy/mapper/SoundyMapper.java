package com.soundy.mapper;

import com.soundy.dto.artist.ArtistShortResp;
import com.soundy.dto.artist.GetArtistResp;
import com.soundy.dto.playlist.CreatePlaylistReq;
import com.soundy.dto.playlist.GetPlayListResp;
import com.soundy.dto.track.PublishTrackReq;
import com.soundy.dto.track.TrackResp;
import com.soundy.dto.track.TrackShortResp;
import com.soundy.entity.Artist;
import com.soundy.entity.Playlist;
import com.soundy.entity.Track;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SoundyMapper {

    SoundyMapper INSTANCE = Mappers.getMapper(SoundyMapper.class);


    // TRACK MAPPERS
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "artists", ignore = true)
    Track toTrack(PublishTrackReq req);

    @Mapping(target = "name", source = "title")
    TrackShortResp toShortTrackResp(Track track);

    TrackResp toTrackResp(Track track);

    // PLAYLIST MAPPERS

    // ARTIST MAPPERS

    @Mapping(target = "name", source = "account.username")
    ArtistShortResp toArtistShortResp(Artist artist);


    @Mapping(target = "name", source = "account.username")
    GetArtistResp toArtistResp(Artist artist);

    @Mapping(target = "description", source = "desc")
    @Mapping(target = "trackList", source = "tracks")
    @Mapping(target = "name", source = "title")
    GetPlayListResp toPlaylistResp(Playlist playlist);

    @Mapping(target = "title", source = "name")
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "id", ignore = true)
    Playlist toPlaylist(CreatePlaylistReq req);

}
