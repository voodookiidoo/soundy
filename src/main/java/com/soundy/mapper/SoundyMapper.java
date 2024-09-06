package com.soundy.mapper;

import com.soundy.dto.artist.AddArtistReq;
import com.soundy.dto.track.PublishTrackReq;
import com.soundy.dto.user.AddUserReq;
import com.soundy.entity.AppUser;
import com.soundy.entity.Artist;
import com.soundy.entity.Track;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SoundyMapper {

    SoundyMapper INSTANCE = Mappers.getMapper(SoundyMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "artists", ignore = true)
    @Mapping(target = "explicit", ignore = true)
    @Mapping(target = "permium", ignore = true)
    @Mapping(target = "username", source = "name")
    AppUser toAppUser(AddUserReq dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "appUsers", ignore = true)
    @Mapping(target = "description", source = "desc")
    Artist toArtist(AddArtistReq dto);


    @Mapping(target = "playlists", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "artists", ignore = true)
    Track toTrack(PublishTrackReq dto);

}
