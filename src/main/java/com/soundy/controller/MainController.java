package com.soundy.controller;

import com.soundy.service.ArtistService;
import com.soundy.service.PlaylistService;
import com.soundy.service.TrackService;
import com.soundy.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/soundy")
@Slf4j
@AllArgsConstructor
@Controller()
public class MainController {
    ArtistService artistService;
    PlaylistService playlistService;
    TrackService trackService;
    UserService userService;
}
