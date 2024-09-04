package com.soundy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class SomeTest {

    //	@MockBean
//	CreatePilotService createService;
//	@Autowired
//	MockMvc mvc;
//	@MockBean
//	IndexPilotService indexService;
//	@MockBean
//	DeletePilotService deleteService;
//	@Autowired
//	ObjectMapper mapper;
//
//	@Test
//	void getReqTest() throws Exception {
//		Mockito.when(indexService.pilotById(1)).thenReturn(Optional.of(
//				mockPilot()
//		));
//		mvc.perform(MockMvcRequestBuilders.get("/pilots/1")).andExpect(
//				MockMvcResultMatchers.status().isOk()
//		);
//	}
//
//	@Test
//	void delReqTest() throws Exception {
//		Mockito.when(deleteService.deletePilot(1)).thenReturn(true);
//		mvc.perform(MockMvcRequestBuilders.delete("/pilots/1")).andExpect(
//				MockMvcResultMatchers.status().isOk());
//		Mockito.when(deleteService.deletePilot(1)).thenReturn(false);
//		mvc.perform(MockMvcRequestBuilders.delete("/pilots/1")).andExpect(
//				MockMvcResultMatchers.status().isNotFound());
//	}
//
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
//	private PilotEntity mockPilot() {
//		return new PilotEntity().setName("Killer").setRank(Rank.SNIPER)
//				.setWeapon(mockWeapon());
//	}
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

}
