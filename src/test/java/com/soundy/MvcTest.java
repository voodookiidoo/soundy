package com.soundy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soundy.dto.track.TrackResp;
import com.soundy.service.ArtistService;
import com.soundy.service.AuthService;
import com.soundy.service.PlaylistService;
import com.soundy.service.TrackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class MvcTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    TrackService trackService;


    @MockBean
    AuthService authService;


    @MockBean
    PlaylistService playlistService;

    @MockBean
    ArtistService artistService;

    @Autowired
    ObjectMapper objectMapper;


//    @Test
//    void getTrackReq() throws Exception {
//        Mockito.when(trackService.findTrackById(1)).thenReturn(Optional.of(mockTrack()));
//        mvc.perform(MockMvcRequestBuilders.get(TRACK_URL + "/1")).andExpect(
//                MockMvcResultMatchers.status().isOk());
//
//        Mockito.when(trackService.findTrackById(2)).thenReturn(Optional.empty());
//        mvc.perform(MockMvcRequestBuilders.get(TRACK_URL + "/2")).andExpect(
//                MockMvcResultMatchers.status().isNotFound());
//
//    }

//    @Test
//    void delTrack() throws Exception {
//        Mockito.when(trackService.delTrackById(1)).thenReturn(true);
//        mvc.perform(MockMvcRequestBuilders.delete(TRACK_URL + "/1")).andExpect(MockMvcResultMatchers.status().isNoContent());
//
//        Mockito.when(trackService.delTrackById(2)).thenReturn(false);
//        mvc.perform(MockMvcRequestBuilders.delete(TRACK_URL + "/2")).andExpect(
//                MockMvcResultMatchers.status().isNotFound());
//    }

    //	@Test
//	void createReqTest() throws Exception {
//		PilotDTO mockPilotDTO = mockPilotDTO();
//		Mockito.when(createService.savePilot(mockPilotDTO)).thenReturn(true);
//		mvc.perform(MockMvcRequestBuilders.put("/pilots/save").contentType(
//				MediaType.APPLICATION_JSON
//		).content(
//				mapper.writeValueAsString(mockPilotDTO)
//		)).andExpect(
//				MockMvcResultMatchers.status().isOk()
//		);
////		Mockito.when(createService.savePilot(mockPilotDTO)).thenReturn(false);
////		mvc.perform(MockMvcRequestBuilders.put("pilots/save")
////				.contentType(MediaType.APPLICATION_JSON).content(
////						new ObjectMapper().writeValueAsBytes(mockPilotDTO)
////				)).andExpect(
////				MockMvcResultMatchers.status().isNotFound()
////		);
//	}
//
//


    //	private PilotDTO mockPilotDTO() {
    //		return new PilotDTO().setName("Booba").setId(10).setWeaponId(Optional.of(10))
    //				.setRank(Optional.of(Rank.SNIPER));
    //	}
    //
    //	private PilotWeaponEntity mockWeapon() {
    //		return new PilotWeaponEntity()
    //				.setId(1)
    //				.setName("G2");
    //	}
    private TrackResp mockTrack() {
        return new TrackResp().setId(1).setExplicit(false).setPremium(true).setTitle("Урбан");
    }

}
