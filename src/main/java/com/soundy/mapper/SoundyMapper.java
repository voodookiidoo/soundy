package com.soundy.mapper;

import com.soundy.dto.artist.AddArtistReq;
import com.soundy.dto.artist.GetArtistResp;
import com.soundy.dto.playlist.CreatePlaylistReq;
import com.soundy.dto.playlist.GetPlayListResp;
import com.soundy.dto.track.PublishTrackReq;
import com.soundy.dto.track.TrackResp;
import com.soundy.dto.track.TrackShortResp;
import com.soundy.dto.user.AddUserReq;
import com.soundy.entity.AppUser;
import com.soundy.entity.Artist;
import com.soundy.entity.Playlist;
import com.soundy.entity.Track;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SoundyMapper {

    SoundyMapper INSTANCE = Mappers.getMapper(SoundyMapper.class);


    @Mapping(target = "subPlaylists", ignore = true)
    @Mapping(target = "playlists", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "artists", ignore = true)
    @Mapping(target = "explicit", ignore = true)
    @Mapping(target = "permium", ignore = true)
    @Mapping(target = "username", source = "name")
    AppUser toAppUser(AddUserReq dto);

    @Mapping(target = "tracks", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "description", source = "desc")
    Artist toArtist(AddArtistReq dto);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "artists", ignore = true)
    Track toTrack(PublishTrackReq dto);


    GetArtistResp toArtistResp(Artist entity);


    @Mapping(target = "name", source = "title")
    TrackShortResp toShortTrackResp(Track track);

    @Mapping(target = "trackList", source = "tracks")
    @Mapping(target = "name", source = "title")
//    @Mapping(target = "listeners", source = "listeners")
    GetPlayListResp toPlaylistResp(Playlist p);


    TrackResp toTrackResp(Track track);

//    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "title", source = "name")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tracks", ignore = true)
    Playlist toPlaylist(CreatePlaylistReq req);
}
